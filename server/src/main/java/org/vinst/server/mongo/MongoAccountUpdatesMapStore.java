package org.vinst.server.mongo;

import com.hazelcast.core.MapStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.vinst.account.AccountUpdateKey;
import org.vinst.common.account.AccountUpdateImpl;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Lars Velsky
 * @since 25/09/14
 */
@Service
public class MongoAccountUpdatesMapStore implements MapStore<AccountUpdateKey, AccountUpdateImpl> {

    private static final Logger log = LoggerFactory.getLogger(MongoAccountUpdatesMapStore.class);

    @Autowired
    private MongoOperations mongoOp;

    @Override
    public void store(AccountUpdateKey key, AccountUpdateImpl value) {
        if (!key.equals(value.getAccountUpdateKey())){
            throw new IllegalArgumentException("Wrong " + key + " for " + value);
        }
        log.debug("Storing {}", value);
        mongoOp.insert(value);
    }

    @Override
    public void storeAll(Map<AccountUpdateKey, AccountUpdateImpl> map) {
        throw new UnsupportedOperationException("Account updates are never saved in batch.");
    }

    @Override
    public void delete(AccountUpdateKey key) {
        throw new UnsupportedOperationException("Account updates are never deleted.");
    }

    @Override
    public void deleteAll(Collection<AccountUpdateKey> keys) {
        throw new UnsupportedOperationException("Account updates are never deleted.");
    }

    @Override
    public AccountUpdateImpl load(AccountUpdateKey key) {
        log.debug("Loading account update for {}", key);
        return mongoOp.findById(key, AccountUpdateImpl.class);
    }

    @Override
    public Map<AccountUpdateKey, AccountUpdateImpl> loadAll(Collection<AccountUpdateKey> keys) {
        if (keys.size() > 5){
            log.debug("Loading {} account updates", keys.size());
        } else {
            log.debug("Loading account updates for {}", keys);
        }

        // todo can this be optimized?
        return keys.stream()
                .map(this::load)
                .collect(Collectors.toMap(
                                AccountUpdateImpl::getAccountUpdateKey,
                                Function.<AccountUpdateImpl>identity()));
    }

    @Override
    public Set<AccountUpdateKey> loadAllKeys() {
        log.debug("Loading all the keys");
        // todo can this be optimized?
        Set<AccountUpdateKey> keys = mongoOp.findAll(AccountUpdateImpl.class).stream()
                .map(AccountUpdateImpl::getAccountUpdateKey)
                .collect(Collectors.toSet());
        log.debug("{} account update keys loaded from db", keys.size());
        return keys;
    }
}
