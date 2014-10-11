package org.vinst.server.dao;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vinst.account.AccountUpdateKey;
import org.vinst.common.Constants;
import org.vinst.common.account.AccountUpdateImpl;

import javax.annotation.PostConstruct;

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
}
