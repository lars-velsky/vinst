package org.vinst.client;

import com.hazelcast.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vinst.account.Account;
import org.vinst.account.AccountKey;
import org.vinst.account.AccountUpdateKey;
import org.vinst.common.Constants;
import org.vinst.common.account.AccountUpdateImpl;
import org.vinst.event.AccountEvent;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Lars Velsky
 * @since 21/09/14.
 */
@Service
public class AccountCache {

    @Autowired
    private HazelcastInstance hzInstance;

    private final Map<AccountKey, AccountBuilder> builders = new HashMap<>();
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @PostConstruct
    public void init(){
        IMap<AccountUpdateKey, AccountUpdateImpl> map = hzInstance.getMap(Constants.ACCOUNT_UPDATES);

        //todo synchronize it and move to start method of lifecycle
        Collection<AccountUpdateImpl> values = map.values();
        for (AccountUpdateImpl value : values) {
            executor.submit(() -> {
                List<AccountEvent> events = value.getEvents();
                for (AccountEvent accountEvent : events) {
                    AccountKey accountKey = accountEvent.getAccountKey();
                    if (!builders.containsKey(accountKey)){
                        builders.put(accountKey, new AccountBuilder(accountKey));
                    }
                    AccountBuilder builder = builders.get(accountKey);
                    accountEvent.visit(builder);
                }
            });
        }

        map.addEntryListener(new EntryListener<AccountUpdateKey, AccountUpdateImpl>() {
            @Override
            public void entryAdded(EntryEvent<AccountUpdateKey, AccountUpdateImpl> event) {
                executor.submit(() -> {
                    List<AccountEvent> events = event.getValue().getEvents();
                    for (AccountEvent accountEvent : events) {
                        AccountKey accountKey = accountEvent.getAccountKey();
                        if (!builders.containsKey(accountKey)){
                            builders.put(accountKey, new AccountBuilder(accountKey));
                        }
                        AccountBuilder builder = builders.get(accountKey);
                        accountEvent.visit(builder);
                    }
                });
            }

            @Override
            public void entryRemoved(EntryEvent<AccountUpdateKey, AccountUpdateImpl> event) {
                throw new IllegalStateException("Account updates should never be removed");
            }

            @Override
            public void entryUpdated(EntryEvent<AccountUpdateKey, AccountUpdateImpl> event) {
                throw new IllegalStateException("Account updates should never be updated");
            }

            @Override
            public void entryEvicted(EntryEvent<AccountUpdateKey, AccountUpdateImpl> event) {

            }

            @Override
            public void mapEvicted(MapEvent event) {

            }

            @Override
            public void mapCleared(MapEvent event) {

            }
        }, true);
    }

    public Optional<Account> getAccount(AccountKey accountKey){
        // todo make it neat functional style and properly synchronized
        if (builders.containsKey(accountKey)){
            return Optional.of(builders.get(accountKey).buildAccount());
        } else {
            return Optional.empty();
        }
    }

    public Set<AccountKey> getAccountKeys(){
        return new HashSet<>(builders.keySet());
    }

    @PreDestroy
    public void stop(){
        executor.shutdown();
    }
}
