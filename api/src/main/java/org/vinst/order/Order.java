package org.vinst.order;

/**
 * <p>An order</p>
 *
 * <p>Order has state and version</p>
 *
 * todo
 *
 * @author lars-velsky
 * @since 03/08/14
 *
 * @see OrderStatus
 */
public interface Order {

    /**
     * <p>Returns this order's key</p>
     *
     * @return order key
     */
    OrderKey getKey();

    /**
     * <p>Returns this order's status</p>
     *
     * @return order status
     *
     * @see OrderStatus
     */
    OrderStatus getStatus();

    /**
     * <p>Returns this order's version</p>
     *
     * @return order version
     */
    long getVersion();
}
