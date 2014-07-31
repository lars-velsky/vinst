package org.vinst.account;

import java.util.concurrent.CompletableFuture;

/**
 * Aggregates operation related to account
 *
 * @author ochtarfear
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
