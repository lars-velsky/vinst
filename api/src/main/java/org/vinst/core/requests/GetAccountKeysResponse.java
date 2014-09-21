package org.vinst.core.requests;

import org.vinst.account.AccountKey;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Lars Velsky
 * @since 21/09/14
 */
public class GetAccountKeysResponse implements CoreResponse, Serializable {

    private final Set<AccountKey> accountKeys;

    public GetAccountKeysResponse(Set<AccountKey> accountKeys) {
        this.accountKeys = accountKeys;
    }

    public Set<AccountKey> getAccountKeys() {
        return accountKeys;
    }
}
