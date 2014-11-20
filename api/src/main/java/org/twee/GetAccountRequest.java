package org.twee;

import org.vinst.account.AccountKey;
import org.vinst.core.requests.CoreRequest;

import java.io.Serializable;

/**
 * @author Lars Velsky
 * @since 19/11/14
 */
public final class GetAccountRequest implements CoreRequest<GetAccountResponse>, Serializable {

    private final AccountKey accountKey;

    public GetAccountRequest(AccountKey accountKey) {
        this.accountKey = accountKey;
    }

    public AccountKey getAccountKey() {
        return accountKey;
    }
}
