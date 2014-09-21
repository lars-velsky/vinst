package org.vinst.common.account;

import org.vinst.account.AccountKey;
import org.vinst.event.AccountCreationEvent;

import java.io.Serializable;

/**
 * @author lars-velsky
 * @since 21/09/14
 */
public class AccountCreationEventImpl implements AccountCreationEvent, Serializable {

    private final AccountKey accountKey;

    public AccountCreationEventImpl(AccountKey accountKey) {
        this.accountKey = accountKey;
    }

    @Override
    public AccountKey getAccountKey() {
        return accountKey;
    }
}
