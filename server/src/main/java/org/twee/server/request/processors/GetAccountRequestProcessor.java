package org.twee.server.request.processors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.twee.GetAccountRequest;
import org.twee.GetAccountResponse;
import org.twee.common.TweeAccountImpl;
import org.vinst.account.Account;
import org.vinst.server.dao.AccountUpdateDAO;
import org.vinst.server.request.RequestProcessor;

/**
 * @author //todo
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
        Account account = accountUpdateDAO.getAccount(request.getAccountKey());
        TweeAccountImpl tweeAccount = new TweeAccountImpl(account);
        return new GetAccountResponse(tweeAccount);
    }
}
