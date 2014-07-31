package org.vinst.common.account;

import org.vinst.account.Account;
import org.vinst.account.AccountKey;

/**
 * @author ochtarfear
 * @since 31/07/14
 */
public class AccountImpl implements Account {


    private final AccountKey key;

    public AccountImpl(AccountKey key) {
        this.key = key;
    }

    @Override
    public AccountKey getAccountKey() {
        return key;
    }

    @Override
    public String toString() {
        return "AccountImpl{" +
                "key=" + key +
                '}';
    }
}
