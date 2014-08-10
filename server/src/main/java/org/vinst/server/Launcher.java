package org.vinst.server;

import org.vinst.account.Account;
import org.vinst.account.AccountKey;
import org.vinst.common.account.AccountImpl;

/**
 * @author lars-velsky
 * @since 31/07/14
 */
public class Launcher {

    public static void main(String[] args) {
        AccountKey accountKey = AccountKey.of(123l);

        Account account = new AccountImpl(accountKey);

        System.out.println("Account: " + account);
    }

}
