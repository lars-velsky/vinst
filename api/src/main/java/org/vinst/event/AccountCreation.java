package org.vinst.event;

import org.vinst.account.AccountKey;

import java.io.Serializable;

/**
 * @author Lars Velsky
 * @since 21/09/14
 */
public final class AccountCreation implements AccountEvent, Serializable {

    private final AccountKey accountKey;

    public AccountCreation(AccountKey accountKey) {
        this.accountKey = accountKey;
    }

    @Override
    public AccountKey getAccountKey() {
        return accountKey;
    }

}
