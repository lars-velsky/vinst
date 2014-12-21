package org.vinst.core.requests;

import org.vinst.account.AccountKey;

import java.io.Serializable;

/**
 * @author Lars Velsky
 * @since 21/12/14
 */
public final class CreatePlainAccountResponse implements CoreResponse, Serializable {

    private final AccountKey accountKey;

    public CreatePlainAccountResponse(AccountKey accountKey) {
        this.accountKey = accountKey;
    }

    public AccountKey getAccountKey(){
        return accountKey;
    }
}
