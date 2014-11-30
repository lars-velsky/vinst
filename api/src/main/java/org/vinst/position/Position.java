package org.vinst.position;

import java.io.Serializable;

/**
 * @author Lars Velsky
 * @since 19/11/14
 */
public final class Position implements Serializable {

    private final PositionKey positionKey;
    private final double quantity;

    public Position(PositionKey positionKey, double quantity) {
        this.positionKey = positionKey;
        this.quantity = quantity;
    }

    public PositionKey getKey() {
        return positionKey;
    }

    public double getQuantity() {
        return quantity;
    }
}
