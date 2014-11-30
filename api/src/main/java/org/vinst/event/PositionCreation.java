package org.vinst.event;

import org.vinst.account.AccountKey;
import org.vinst.position.PositionKey;

import java.io.Serializable;

/**
 * @author Lars Velsky
 * @since 19/11/14
 */
public final class PositionCreation implements PositionEvent, Serializable {

    private final double initialQuantity;
    private final PositionKey positionKey;
    private final AccountKey accountKey;

    public PositionCreation(double initialQuantity, PositionKey positionKey, AccountKey accountKey) {
        this.initialQuantity = initialQuantity;
        this.positionKey = positionKey;
        this.accountKey = accountKey;
    }

    public double getInitialQuantity() {
        return initialQuantity;
    }

    public PositionKey getPositionKey() {
        return positionKey;
    }

    @Override
    public AccountKey getAccountKey() {
        return accountKey;
    }

}
