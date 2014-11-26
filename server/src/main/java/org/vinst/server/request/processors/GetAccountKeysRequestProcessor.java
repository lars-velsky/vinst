package org.vinst.server.request.processors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vinst.core.requests.GetAccountKeysRequest;
import org.vinst.core.requests.GetAccountKeysResponse;
import org.vinst.server.dao.AccountUpdateDAO;
import org.vinst.server.request.RequestProcessor;

/**
 * @author Lars Velsky
 * @since 22/09/14
 */
@Component
public class GetAccountKeysRequestProcessor implements RequestProcessor<GetAccountKeysRequest, GetAccountKeysResponse> {

    @Autowired
    private AccountUpdateDAO accountUpdateDAO;

    @Override
    public Class<GetAccountKeysRequest> getRequestClass() {
        return GetAccountKeysRequest.class;
    }

    @Override
    public GetAccountKeysResponse processRequest(GetAccountKeysRequest request) {
        return new GetAccountKeysResponse(accountUpdateDAO.getAccountKeys());
    }
}
