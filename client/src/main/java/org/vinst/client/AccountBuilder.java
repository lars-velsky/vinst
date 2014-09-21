package org.vinst.client;

import org.vinst.account.Account;
import org.vinst.account.AccountKey;
import org.vinst.common.account.AccountImpl;
import org.vinst.event.AccountCreationEvent;
import org.vinst.event.Event;

/**
 * @author Lars Velsky
 * @since 21/09/14
 */
class AccountBuilder implements Event.Visitor {

    private final AccountKey accountKey;
    private final long currentVersion = 0;

    AccountBuilder(AccountKey accountKey) {
        this.accountKey = accountKey;
    }

    Account buildAccount(){
        return new AccountImpl(accountKey, currentVersion);
    }

    @Override
    public void visitAccountCreation(AccountCreationEvent event) {
        // do nothing yet
    }
}
