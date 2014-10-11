package org.vinst.server.mongo;

import com.hazelcast.core.MapStore;
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

    @Autowired
    private MongoOperations mongoOp;

    @Override
    public void store(AccountUpdateKey key, AccountUpdateImpl value) {
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
        return mongoOp.findById(key, AccountUpdateImpl.class);
    }

    @Override
    public Map<AccountUpdateKey, AccountUpdateImpl> loadAll(Collection<AccountUpdateKey> keys) {
        // todo can this be optimized?
        return keys.stream()
                .map(this::load)
                .collect(Collectors.toMap(
                                AccountUpdateImpl::getAccountUpdateKey,
                                Function.<AccountUpdateImpl>identity()));
    }

    @Override
    public Set<AccountUpdateKey> loadAllKeys() {
        // todo can this be optimized?
        return mongoOp.findAll(AccountUpdateImpl.class).stream()
                .map(AccountUpdateImpl::getAccountUpdateKey)
                .collect(Collectors.toSet());
    }
}
