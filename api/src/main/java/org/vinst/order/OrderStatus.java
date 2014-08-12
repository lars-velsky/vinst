package org.vinst.order;

/**
 * <p>Order status</p>
 *
 * todo
 *
 * @author lars-velsky
 * @since 03/08/14
 *
 * @see Order
 */
public enum OrderStatus {

    /**
     * Newly create orders
     */
    NEW,

    /**
     * Orders that has already passed initial processing
     * (such as validation) but not yet finished
     */
    ACTIVE,

    /**
     * Orders that has been rejected by the system (e.g. invalid orders)
     */
    REJECTED,

    /**
     * Orders that has been cancelled by user
     */
    CANCELLED,

    /**
     * Orders that has been completed
     */
    COMPLETED
}
