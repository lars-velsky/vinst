package org.vinst.common.account;

import org.vinst.position.Position;
import org.vinst.position.PositionKey;

import java.io.Serializable;

/**
 * @author Lars Velsky
 * @since 19/11/14
 */
public class PositionImpl implements Position, Serializable {


    private final PositionKey positionKey;
    private final double quantity;

    public PositionImpl(PositionKey positionKey, double quantity) {
        this.positionKey = positionKey;
        this.quantity = quantity;
    }

    @Override
    public PositionKey getKey() {
        return positionKey;
    }

    @Override
    public double getQuantity() {
        return quantity;
    }
}
