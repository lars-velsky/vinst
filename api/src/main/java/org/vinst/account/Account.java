package org.vinst.account;

import java.util.Map;
import java.util.Optional;

/**
 * <p>An account.</p>
 *
 * <p>Orders and positions constitute account.</p>
 *
 * todo
 *
 * @author lars-velsky
 * @since 31/07/14
 *
 * @see Order
 * @see Position
 */
public interface Account {

    /**
     * <p>Returns this account's key.</p>
     *
     * @return account key
     */
    AccountKey getAccountKey();

    /**
     * <p>Returns this account's version.</p>
     *
     * @return account version
     */
    long getVersion();

    /**
     * <p>Returns a map that contains all the orders
     * this account contains.</p>
     *
     * @return orders
     *
     * @see org.vinst.account.Order
     */
    Map<OrderKey, Order> getOrders();

    /**
     * <p>Returns an order by its key.</p>
     *
     * <p>Uses {@link Optional} since this account
     * may not contain the order with the given key</p>
     *
     * @param orderKey order key
     *
     * @return order
     *
     * @see Order
     */
    Optional<Order> getOrder(OrderKey orderKey);

    /**
     * <p>Returns a map that contains all the positions
     * this account contains.</p>
     *
     * @return positions
     *
     * @see org.vinst.account.Position
     */
    Map<PositionKey, Position> getPositions();

    /**
     * <p>Returns a position by its key.</p>
     *
     * <p>Uses {@link Optional} since this account
     * may not contain the position with the given key</p>
     *
     * @param positionKey position key
     *
     * @return position
     *
     * @see Position
     */
    Optional<Position> getPosition(PositionKey positionKey);

}
