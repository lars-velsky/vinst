package org.twee.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.twee.TweeAccount;
import org.twee.USDTransferRequest;
import org.twee.USDTransferResponse;
import org.vinst.account.Account;
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
        return accountUpdateDAO.getAccount(request.getAccountKey())
                .map(account -> {
                    try {
                        double quantity = request.getQuantity();
                        return new USDTransferResponse(addToBalance(quantity, account));
                    } catch (Exception e) {
                        return new USDTransferResponse(e);
                    }
                })
                .orElse(new USDTransferResponse(new IllegalArgumentException("No account with " + request.getAccountKey())));
    }

    private double addToBalance(double quantity, Account account) {
        PositionUpdate positionUpdate = new PositionUpdate(account.getAccountKey(), TweeAccount.BALANCE_POSITION_KEY, quantity);
        AccountUpdateKey accountUpdateKey = AccountUpdateKey.of(account.getAccountKey(), account.getVersion() + 1);
        AccountUpdateImpl accountUpdate = new AccountUpdateImpl(accountUpdateKey, Collections.singletonList(positionUpdate));

        // todo synchronization needed
        accountUpdateDAO.saveAccountUpdate(accountUpdate);

        return new TweeAccount(account).getBalanceUSD() + quantity;
    }
}
