package org.vinst.event;

import org.vinst.position.PositionKey;

/**
 * @author Lars Velsky
 * @since 15/11/14
 */
public interface PositionEvent extends AccountEvent {

    PositionKey getPositionKey();

}
