package org.vinst.server.dao;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vinst.account.Account;
import org.vinst.account.AccountKey;
import org.vinst.account.AccountUpdate;
import org.vinst.account.AccountUpdateKey;
import org.vinst.common.Constants;
import org.vinst.common.account.AccountCreationEventImpl;
import org.vinst.common.account.AccountImpl;
import org.vinst.common.account.AccountUpdateImpl;
import org.vinst.common.account.PositionImpl;
import org.vinst.event.AccountCreationEvent;
import org.vinst.event.AccountEvent;
import org.vinst.event.Event;
import org.vinst.event.PositionCreate;
import org.vinst.position.Position;
import org.vinst.position.PositionKey;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Lars Velsky
 * @since 11/10/14
 */
@Service
public class AccountUpdateDAO {

    private static final Logger log = LoggerFactory.getLogger(AccountUpdateDAO.class);

    @Autowired
    private HazelcastInstance hz;

    @PostConstruct
    @SuppressWarnings("unused")
    private void init(){
        // populate the map
        getMap();
    }

    private IMap<AccountUpdateKey, AccountUpdateImpl> getMap() {
        return hz.getMap(Constants.ACCOUNT_UPDATES);
    }

    public void saveAccountUpdate(AccountUpdateImpl accountUpdate){
        log.debug("Saving account update {}", accountUpdate);
        // even though mongo should not let us save account updates with
        // identical keys, we still use puIfAbsent() just in case we
        // would want to go without persistence some time
        // todo this method calls equals() on byte[]s not on objects - make sure it's ok
        if (getMap().putIfAbsent(accountUpdate.getAccountUpdateKey(), accountUpdate) != null){
            throw new IllegalArgumentException("Account update with " + accountUpdate.getAccountUpdateKey() + " is already present in the map");
        }
    }

    public Set<AccountKey> getAccountKeys() {
        // todo this should be done via account cache or accounts map
        log.debug("Collecting created account keys");
        return getMap().values().stream()
                .flatMap(au -> au.getEvents().stream())
                .filter(e -> e.getClass().equals(AccountCreationEventImpl.class))
                .map(AccountEvent::getAccountKey)
                .collect(Collectors.toSet());
    }

    public Account getAccount(AccountKey accountKey){
        log.debug("Trying to build account {}", accountKey);
        AccountBuilder builder = new AccountBuilder(accountKey, -1);

        getMap().values().stream()
                .filter(au -> au.getAccountUpdateKey().getAccountKey().equals(accountKey))
                .sorted((au1, au2) -> (int) (au1.getAccountUpdateKey().getVersion() - au2.getAccountUpdateKey().getVersion()))
                .forEach(builder::applyUpdate);

        return builder.toAccount();
    }

    private static class AccountBuilder implements Event.Visitor {

        private final AccountKey accountKey;
        private long version;
        // todo position builders should be used here
        private Map<PositionKey, Position> positions = new HashMap<>();

        private AccountBuilder(AccountKey accountKey, long version) {
            this.accountKey = accountKey;
            this.version = version;
        }

        private void applyUpdate(AccountUpdate update){
            if (update.getAccountUpdateKey().getVersion() != version + 1){
                throw new IllegalArgumentException("Account updates should be applied sequentially");
            }

            version++;

            update.getEvents().forEach(e -> e.visit(this));
        }

        private Account toAccount(){
            return new AccountImpl(accountKey, version, positions);
        }

        @Override
        public void visitAccountCreation(AccountCreationEvent event) {
            // do nothing
        }

        @Override
        public void visitPositionCreate(PositionCreate positionCreate) {
            positions.put(positionCreate.getPositionKey(),
                    new PositionImpl(positionCreate.getPositionKey(), positionCreate.getInitialQuantity()));
        }
    }
}
