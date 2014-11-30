package org.vinst.event;

import org.vinst.account.AccountKey;
import org.vinst.position.PositionKey;

import java.io.Serializable;

/**
 * @author Lars Velsky
 * @since 14/11/14
 */
public final class PositionUpdate implements PositionEvent, Serializable {

    private final AccountKey accountKey;
    private final PositionKey positionKey;
    private final double quantityDelta;

    public PositionUpdate(AccountKey accountKey, PositionKey positionKey, double quantityDelta) {
        this.accountKey = accountKey;
        this.positionKey = positionKey;
        this.quantityDelta = quantityDelta;
    }

    @Override
    public AccountKey getAccountKey() {
        return accountKey;
    }

    @Override
    public PositionKey getPositionKey() {
        return positionKey;
    }

    public double getQuantityDelta(){
        return quantityDelta;
    }

}
