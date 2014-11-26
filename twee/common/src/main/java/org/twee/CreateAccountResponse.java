package org.twee;

import org.vinst.account.AccountKey;
import org.vinst.core.requests.CoreResponse;

import java.io.Serializable;

/**
 * Response for create account request.
 *
 * @author Sergey Mischenko
 * @since 06.09.2014
 */
public final class CreateAccountResponse implements CoreResponse, Serializable {

    private final AccountKey accountKey;

    public CreateAccountResponse(AccountKey accountKey) {
        this.accountKey = accountKey;
    }

    /**
     * Returns key of created account.
     *
     * @return key of created account
     */
    public AccountKey getAccountKey() {
        return accountKey;
    }
}
