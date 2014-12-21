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
import org.vinst.event.AccountCreation;
import org.vinst.common.account.AccountUpdateImpl;
import org.vinst.event.PositionCreation;
import org.vinst.event.PositionUpdate;
import org.vinst.position.Position;
import org.vinst.event.AccountEvent;
import org.vinst.position.PositionKey;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
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
        // todo this method calls equals() on byte[]s not on objects - make sure it's ok
        if (getMap().putIfAbsent(accountUpdate.getAccountUpdateKey(), accountUpdate) != null){
            throw new IllegalArgumentException("Account update with " + accountUpdate.getAccountUpdateKey() + " is already present in the map");
        }
    }

    public Set<AccountKey> getAccountKeys() {
        // todo this should be done via account cache or accounts map
        log.debug("Collecting account keys");
        return getMap().values().stream()
                .flatMap(au -> au.getEvents().stream())
                .filter(e -> e.getClass().equals(AccountCreation.class))
                .map(AccountEvent::getAccountKey)
                .collect(Collectors.toSet());
    }

    public Optional<Account> getAccount(AccountKey accountKey){
        // todo this should be done via account cache or accounts map
        log.debug("Trying to build account {}", accountKey);
        AccountBuilder builder = new AccountBuilder(accountKey, -1);

        getMap().values().stream()
                .filter(au -> au.getAccountUpdateKey().getAccountKey().equals(accountKey))
                .sorted((au1, au2) -> (int) (au1.getAccountUpdateKey().getVersion() - au2.getAccountUpdateKey().getVersion()))
                .forEach(builder::applyUpdate);

        return builder.toAccount();
    }

    private static class AccountBuilder {

        private final AccountKey accountKey;
        private long version;
        private Map<PositionKey, PositionBuilder> positionBuilders = new HashMap<>();

        private AccountBuilder(AccountKey accountKey, long version) {
            this.accountKey = accountKey;
            this.version = version;
        }

        private void applyUpdate(AccountUpdate update){
            if (update.getAccountUpdateKey().getVersion() != version + 1){
                throw new IllegalArgumentException("Account updates should be applied sequentially");
            }

            version++;

            // todo ugly 'instanceof' code. make it nice
            update.getEvents().forEach(e -> {
                if (e instanceof PositionCreation){
                    PositionCreation positionCreation = (PositionCreation) e;
                    PositionKey positionKey = positionCreation.getPositionKey();
                    positionBuilders.put(positionKey,
                            new PositionBuilder(positionKey, positionCreation.getInitialQuantity()));
                } else if (e instanceof PositionUpdate){
                    PositionUpdate positionUpdate = (PositionUpdate) e;
                    PositionBuilder positionBuilder = positionBuilders.get(positionUpdate.getPositionKey());
                    positionBuilder.updateQuantity(positionUpdate.getQuantityDelta());
                }
            });
        }

        private Optional<Account> toAccount(){
            Map<PositionKey, Position> positions = positionBuilders.values().stream()
                    .map(PositionBuilder::toPosition)
                    .collect(Collectors.toMap(Position::getKey, Function.identity()));

            // todo not very elegant
            return version == -1? Optional.empty():
                    Optional.of(new Account(accountKey, version, positions));
        }

    }

    private static class PositionBuilder {

        private final PositionKey key;
        private double quantity;

        private PositionBuilder(PositionKey key, double quantity) {
            this.key = key;
            this.quantity = quantity;
        }

        public void updateQuantity(double quantityDelta) {
            this.quantity += quantityDelta;
        }

        Position toPosition(){
            return new Position(key, quantity);
        }
    }
}
