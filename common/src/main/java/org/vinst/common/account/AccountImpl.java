package org.vinst.common.account;

import org.vinst.account.*;
import org.vinst.order.Order;
import org.vinst.order.OrderKey;
import org.vinst.position.Position;
import org.vinst.position.PositionKey;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * @author lars-velsky
 * @since 31/07/14
 */
public class AccountImpl implements Account {


    private final AccountKey key;

    public AccountImpl(AccountKey key) {
        this.key = key;
    }

    @Override
    public AccountKey getAccountKey() {
        return key;
    }

    @Override
    public long getVersion() {
        // todo
        return 0;
    }

    @Override
    public Map<OrderKey, Order> getOrders() {
        // todo
        return Collections.emptyMap();
    }

    @Override
    public Optional<Order> getOrder(OrderKey orderKey) {
        // todo
        return Optional.empty();
    }

    @Override
    public Map<PositionKey, Position> getPositions() {
        // todo
        return Collections.emptyMap();
    }

    @Override
    public Optional<Position> getPosition(PositionKey positionKey) {
        // todo
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "AccountImpl{" +
                "key=" + key +
                '}';
    }
}
