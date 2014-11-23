package org.twee.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.twee.CreateAccountRequest;
import org.twee.CreateAccountResponse;
import org.twee.USD;
import org.vinst.account.AccountKey;
import org.vinst.account.AccountUpdateKey;
import org.vinst.common.account.AccountCreationEventImpl;
import org.vinst.common.account.AccountUpdateImpl;
import org.vinst.common.account.PositionCreateImpl;
import org.vinst.event.AccountEvent;
import org.vinst.position.PositionKey;
import org.vinst.server.dao.AccountUpdateDAO;
import org.vinst.server.request.RequestProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Lars Velsky
 * @since 22/09/14
 */
@Component
public class CreateAccountRequestProcessor implements RequestProcessor<CreateAccountRequest, CreateAccountResponse> {

    private static Logger log = LoggerFactory.getLogger(CreateAccountRequestProcessor.class);

    private static final Random rnd = new Random();

    @Autowired
    private AccountUpdateDAO accountUpdateDAO;

    @Override
    public Class<CreateAccountRequest> getRequestClass() {
        return CreateAccountRequest.class;
    }

    @Override
    public CreateAccountResponse processRequest(CreateAccountRequest request) {
        // we do not lock anything yet - we take an optimistic approach:
        // because account update key consist of an account key AND version
        // accountUpdateDAO shouldn't let us insert an already present
        // account update
        // for now just let it throw an exception
        final long id = rnd.nextLong();
        AccountKey accountKey = AccountKey.of(id);

        List<AccountEvent> events = new ArrayList<>();

        events.add(new AccountCreationEventImpl(accountKey));
        events.add(new PositionCreateImpl(0, PositionKey.of(USD.KEY), accountKey));

        AccountUpdateKey accountUpdateKey = AccountUpdateKey.of(accountKey, 0);
        AccountUpdateImpl accountUpdate = new AccountUpdateImpl(accountUpdateKey, events);

        accountUpdateDAO.saveAccountUpdate(accountUpdate);
        log.info("Account with id {} created", id);
        return new CreateAccountResponse(accountKey);
    }
}
