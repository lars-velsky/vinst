package org.vinst.server;

import com.hazelcast.core.MapStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vinst.account.AccountUpdateKey;
import org.vinst.common.account.AccountUpdateImpl;
import org.vinst.server.mongo.AccountUpdateRepository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Lars Velsky
 * @since 25/09/14
 */
@Service
public class AccountUpdatesMapStore implements MapStore<AccountUpdateKey, AccountUpdateImpl> {

    @Autowired
    private AccountUpdateRepository repository;

    @Override
    public void store(AccountUpdateKey key, AccountUpdateImpl value) {
        repository.save(value);
    }

    @Override
    public void storeAll(Map<AccountUpdateKey, AccountUpdateImpl> map) {
        repository.save(map.values());
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
        return repository.findOne(key);
    }

    @Override
    public Map<AccountUpdateKey, AccountUpdateImpl> loadAll(Collection<AccountUpdateKey> keys) {
        HashMap<AccountUpdateKey, AccountUpdateImpl> data = new HashMap<>();
        Iterable<AccountUpdateImpl> all = repository.findAll(keys);
        all.forEach(a -> data.put(a.getAccountUpdateKey(), a));
        return data;
    }

    @Override
    public Set<AccountUpdateKey> loadAllKeys() {
        // todo can this be optimized?
        List<AccountUpdateImpl> all = repository.findAll();
        return all.stream().map(AccountUpdateImpl::getAccountUpdateKey).collect(Collectors.toSet());
    }
}
