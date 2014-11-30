package org.vinst.account;

import org.vinst.order.Order;
import org.vinst.order.OrderKey;
import org.vinst.position.Position;
import org.vinst.position.PositionKey;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Lars Velsky
 * @since 31/07/14
 */
public final class Account implements Serializable {


    private final AccountKey key;
    private final long version;
    private Map<PositionKey, Position> positions;

    public Account(AccountKey key, long version, Map<PositionKey, Position> positions) {
        this.key = key;
        this.version = version;
        this.positions = Collections.unmodifiableMap(new HashMap<>(positions));
    }

    public AccountKey getAccountKey() {
        return key;
    }

    public long getVersion() {
        return version;
    }

    public Map<OrderKey, Order> getOrders() {
        // todo
        return Collections.emptyMap();
    }

    public Optional<Order> getOrder(OrderKey orderKey) {
        // todo
        return Optional.empty();
    }

    public Map<PositionKey, Position> getPositions() {
        return positions;
    }

    public Optional<Position> getPosition(PositionKey positionKey) {
        return Optional.ofNullable(positions.get(positionKey));
    }

    @Override
    public String toString() {
        return "AccountImpl{" +
                "key=" + key +
                '}';
    }
}
