package org.vinst.server.dao;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
}
