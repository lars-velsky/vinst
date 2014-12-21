package org.vinst.server.request.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vinst.account.AccountKey;
import org.vinst.account.AccountUpdateKey;
import org.vinst.common.account.AccountUpdateImpl;
import org.vinst.core.requests.CreatePlainAccountRequest;
import org.vinst.core.requests.CreatePlainAccountResponse;
import org.vinst.event.AccountCreation;
import org.vinst.event.AccountEvent;
import org.vinst.server.dao.AccountUpdateDAO;
import org.vinst.server.request.RequestProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Lars Velsky
 * @since 21/12/14
 */
@Component
public class CreatePlainAccountRequestProcessor implements RequestProcessor<CreatePlainAccountRequest, CreatePlainAccountResponse> {

    private static Logger log = LoggerFactory.getLogger(CreatePlainAccountRequestProcessor.class);

    private static final Random rnd = new Random();

    @Autowired
    private AccountUpdateDAO accountUpdateDAO;

    @Override
    public Class<CreatePlainAccountRequest> getRequestClass() {
        return CreatePlainAccountRequest.class;
    }

    @Override
    public CreatePlainAccountResponse processRequest(CreatePlainAccountRequest request) {
        // we do not lock anything yet - we take an optimistic approach:
        // because account update key consist of an account key AND version
        // accountUpdateDAO shouldn't let us insert an already present
        // account update
        // for now just let it throw an exception
        final long id = rnd.nextLong();
        AccountKey accountKey = AccountKey.of(id);

        List<AccountEvent> events = new ArrayList<>();

        events.add(new AccountCreation(accountKey));

        AccountUpdateKey accountUpdateKey = AccountUpdateKey.of(accountKey, 0);
        AccountUpdateImpl accountUpdate = new AccountUpdateImpl(accountUpdateKey, events);

        accountUpdateDAO.saveAccountUpdate(accountUpdate);
        log.info("Account with id {} created", id);
        return new CreatePlainAccountResponse(accountKey);
    }
}
