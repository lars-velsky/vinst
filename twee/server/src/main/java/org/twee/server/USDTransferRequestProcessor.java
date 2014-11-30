package org.twee.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.twee.TweeAccount;
import org.twee.USDTransferRequest;
import org.twee.USDTransferResponse;
import org.vinst.account.Account;
import org.vinst.account.AccountKey;
import org.vinst.account.AccountUpdateKey;
import org.vinst.common.account.AccountUpdateImpl;
import org.vinst.event.PositionUpdate;
import org.vinst.server.dao.AccountUpdateDAO;
import org.vinst.server.request.RequestProcessor;

import java.util.Collections;

/**
 * @author Lars Velsky
 * @since 27/11/14
 */
@Component
public class USDTransferRequestProcessor implements RequestProcessor<USDTransferRequest, USDTransferResponse> {

    @Autowired
    private AccountUpdateDAO accountUpdateDAO;

    @Override
    public Class<USDTransferRequest> getRequestClass() {
        return USDTransferRequest.class;
    }

    @Override
    public USDTransferResponse processRequest(USDTransferRequest request) {
        // todo synchronization needed
        AccountKey accountKey = request.getAccountKey();
        Account account = accountUpdateDAO.getAccount(accountKey);

        PositionUpdate positionUpdate = new PositionUpdate(accountKey, TweeAccount.POSITION_KEY, request.getQuantity());
        AccountUpdateKey accountUpdateKey = AccountUpdateKey.of(accountKey, account.getVersion() + 1);
        AccountUpdateImpl accountUpdate = new AccountUpdateImpl(accountUpdateKey, Collections.singletonList(positionUpdate));

        accountUpdateDAO.saveAccountUpdate(accountUpdate);

        return new USDTransferResponse();
    }
}
