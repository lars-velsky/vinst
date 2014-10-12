package org.vinst.server.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vinst.account.AccountKey;
import org.vinst.account.AccountUpdateKey;
import org.vinst.common.account.AccountCreationEventImpl;
import org.vinst.common.account.AccountUpdateImpl;
import org.vinst.core.requests.*;
import org.vinst.server.dao.AccountUpdateDAO;

import java.util.Collections;
import java.util.Random;

/**
 * @author lars-velsky
 * @since 15/09/14
 */
@Service
public class RequestsProcessor {

    private static Logger log = LoggerFactory.getLogger(RequestsProcessor.class);

    private static final Random rnd = new Random();

    @Autowired
    private AccountUpdateDAO accountUpdateDAO;

    public CoreResponse process(CoreRequest request){
        // todo introduce processors registry
        log.debug("Processing request {}", request.getClass().getSimpleName());
        if (request instanceof CreateAccountRequest){
            return createAccount();
        } else if (request instanceof GetAccountKeysRequest){
            return getAccountKeys();
        }
        throw new UnsupportedOperationException(request.getClass().getSimpleName() + " requests aren't supported");
    }

    private GetAccountKeysResponse getAccountKeys() {
        return new GetAccountKeysResponse(accountUpdateDAO.getAccountKeys());
    }

    private CreateAccountResponse createAccount() {
        // we do not lock anything yet - we take an optimistic approach:
        // because account update key consist of an account key AND version
        // mongo won't let us insert an invalid account update
        // for now just throw an exception
        final long id = rnd.nextLong();
        AccountKey accountKey = AccountKey.of(id);
        AccountCreationEventImpl accountCreationEvent = new AccountCreationEventImpl(accountKey);
        AccountUpdateKey accountUpdateKey = AccountUpdateKey.of(accountKey, 0);
        AccountUpdateImpl accountUpdate = new AccountUpdateImpl(accountUpdateKey, Collections.singletonList(accountCreationEvent));
        accountUpdateDAO.saveAccountUpdate(accountUpdate);
        log.info("Account with id {} created", id);
        return new CreateAccountResponse(accountKey);
    }
}
