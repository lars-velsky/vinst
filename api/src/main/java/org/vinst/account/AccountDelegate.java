package org.vinst.account;

import org.vinst.order.Order;
import org.vinst.order.OrderKey;
import org.vinst.position.Position;
import org.vinst.position.PositionKey;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

/**
 * @author Lars Velsky
 * @since 14/11/14
 */
public abstract class AccountDelegate implements Account, Serializable {

    private final Account account;

    protected AccountDelegate(Account account){
        this.account = account;
    }

    @Override
    public AccountKey getAccountKey() {
        return account.getAccountKey();
    }

    @Override
    public long getVersion() {
        return account.getVersion();
    }

    @Override
    public Map<OrderKey, Order> getOrders() {
        return account.getOrders();
    }

    @Override
    public Optional<Order> getOrder(OrderKey orderKey) {
        return account.getOrder(orderKey);
    }

    @Override
    public Map<PositionKey, Position> getPositions() {
        return account.getPositions();
    }

    @Override
    public Optional<Position> getPosition(PositionKey positionKey) {
        return account.getPosition(positionKey);
    }
}
