package org.vinst.account;

import java.util.concurrent.CompletableFuture;

/**
 * <p>Aggregates operation related to account</p>
 *
 * @author lars-velsky
 * @since 31/07/14
 */
public interface AccountModule {


    /**
     * Returns {@link org.vinst.account.Account}
     * by the specified {@link AccountKey}
     *
     * @param accountKey key of the account
     * @return account
     */
    CompletableFuture<Account> getAccount(AccountKey accountKey);

}
