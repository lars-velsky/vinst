package org.vinst.order;

/**
 * <p>An order key</p>
 *
 * todo
 *
 * @author Lars Velsky
 * @since 03/08/14
 *
 * @see Order
 */
public final class OrderKey {

    private final long id;

    public static OrderKey of(long id){
        return new OrderKey(id);
    }

    private OrderKey(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "OrderKey{" +
                "id=" + id +
                '}';
    }
}
