package org.vinst.core.cluster;

import org.vinst.core.AccountCreateRequest;
import org.vinst.core.AccountCreateResponse;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public class AccountCreateRequestProcessor implements RequestProcessor<AccountCreateRequest, AccountCreateResponse> {
    @Override
    public Class<AccountCreateRequest> getRequestClass() {
        return AccountCreateRequest.class;
    }

    @Override
    public AccountCreateResponse process(AccountCreateRequest request) {
        return new AccountCreateResponse();
    }
}
