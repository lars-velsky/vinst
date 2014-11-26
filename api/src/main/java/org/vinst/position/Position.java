package org.vinst.position;

/**
 * <p>A position</p>
 *
 * <p>Positions are what an account has</p>
 *
 * todo
 *
 * @author Lars Velsky
 * @since 03/08/14
 */
public interface Position {

    /**
     * <p>Returns this position's key</p>
     *
     * @return position key
     */
    PositionKey getKey();

    double getQuantity();
}