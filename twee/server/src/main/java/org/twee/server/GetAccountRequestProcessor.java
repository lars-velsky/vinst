package org.twee.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.twee.GetAccountRequest;
import org.twee.GetAccountResponse;
import org.twee.TweeAccount;
import org.vinst.account.Account;
import org.vinst.server.dao.AccountUpdateDAO;
import org.vinst.server.request.RequestProcessor;

import java.util.Optional;

/**
 * @author Lars Velsky
 * @since 19/11/14
 */
@Component
public class GetAccountRequestProcessor implements RequestProcessor<GetAccountRequest, GetAccountResponse> {

    @Autowired
    private AccountUpdateDAO accountUpdateDAO;

    @Override
    public Class<GetAccountRequest> getRequestClass() {
        return GetAccountRequest.class;
    }

    @Override
    public GetAccountResponse processRequest(GetAccountRequest request) {
        Optional<Account> account = accountUpdateDAO.getAccount(request.getAccountKey());
        return new GetAccountResponse(account.map(TweeAccount::new)
                .orElseThrow(() -> new IllegalArgumentException("No account with " + request.getAccountKey())));
    }
}
