package org.vinst.server.dao;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vinst.account.AccountKey;
import org.vinst.account.AccountUpdateKey;
import org.vinst.common.Constants;
import org.vinst.common.account.AccountCreationEventImpl;
import org.vinst.common.account.AccountUpdateImpl;
import org.vinst.event.AccountEvent;

import javax.annotation.PostConstruct;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Lars Velsky
 * @since 11/10/14
 */
@Service
public class AccountUpdateDAO {

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
        getMap().put(accountUpdate.getAccountUpdateKey(), accountUpdate);
    }

    public Set<AccountKey> getAccountKeys() {
        // todo this should be done via account cache or accounts map
        return getMap().values().stream()
                .flatMap(au -> au.getEvents().stream())
                .filter(e -> e.getClass().equals(AccountCreationEventImpl.class))
                .map(AccountEvent::getAccountKey)
                .collect(Collectors.toSet());
    }
}
