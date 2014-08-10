package org.vinst.client;

import org.vinst.account.Account;
import org.vinst.account.AccountKey;
import org.vinst.account.AccountModule;
import org.vinst.common.account.AccountImpl;

import java.util.concurrent.CompletableFuture;

/**
 * @author lars-velsky
 * @since 31/07/14
 */
public class AccountModuleImpl implements AccountModule {

    @Override
    public CompletableFuture<Account> getAccount(AccountKey accountKey) {
        CompletableFuture<Account> accountCompletableFuture = new CompletableFuture<>();
        accountCompletableFuture.complete(new AccountImpl(accountKey));
        return accountCompletableFuture;
    }
}
