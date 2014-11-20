package org.vinst.event;

import org.vinst.position.PositionKey;

/**
 * @author Lars Velsky
 * @since 14/11/14
 */
public interface PositionUpdate extends AccountEvent {

    PositionKey positionKey();

    double quantityDelta();
}
