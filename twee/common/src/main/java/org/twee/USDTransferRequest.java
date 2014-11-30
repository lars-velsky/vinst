package org.twee;

import org.vinst.account.AccountKey;
import org.vinst.core.requests.CoreRequest;

import java.io.Serializable;

/**
 * @author Lars Velsky
 * @since 15/11/14
 */
public final class USDTransferRequest implements CoreRequest<USDTransferResponse>, Serializable {

    private final AccountKey accountKey;
    private final double quantity;

    public USDTransferRequest(AccountKey accountKey, double quantity) {
        if (accountKey == null){
            throw new IllegalArgumentException("Account key should not be null");
        }
        if (quantity == 0){
            throw new IllegalArgumentException("Why transfer 0 dollars?");
        }

        this.accountKey = accountKey;
        this.quantity = quantity;
    }

    public AccountKey getAccountKey(){
        return accountKey;
    }


    public double getQuantity(){
        return quantity;
    }
}
