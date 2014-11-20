package org.vinst.common.account;

import org.vinst.account.AccountKey;
import org.vinst.event.PositionCreate;
import org.vinst.position.PositionKey;

import java.io.Serializable;

/**
 * @author Lars Velsky
 * @since 19/11/14
 */
public class PositionCreateImpl implements PositionCreate, Serializable {


    private final double initialQuantity;
    private final PositionKey positionKey;
    private final AccountKey accountKey;

    public PositionCreateImpl(double initialQuantity, PositionKey positionKey, AccountKey accountKey) {
        this.initialQuantity = initialQuantity;
        this.positionKey = positionKey;
        this.accountKey = accountKey;
    }

    @Override
    public double getInitialQuantity() {
        return initialQuantity;
    }

    @Override
    public PositionKey getPositionKey() {
        return positionKey;
    }

    @Override
    public AccountKey getAccountKey() {
        return accountKey;
    }

    @Override
    public void visit(Visitor visitor) {
        visitor.visitPositionCreate(this);
    }
}
