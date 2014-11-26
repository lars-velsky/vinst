package org.twee;

import org.vinst.account.Account;
import org.vinst.account.AccountDelegate;
import org.vinst.position.PositionKey;

import java.io.Serializable;

/**
 * @author Lars Velsky
 * @since 14/11/14
 */
public final class TweeAccount extends AccountDelegate implements Serializable {

    public static final PositionKey POSITION_KEY = PositionKey.of(USD.KEY);

    public TweeAccount(Account account) {
        super(account);

        if (!account.getPositions().containsKey(POSITION_KEY)) {
            throw new IllegalArgumentException("Account " + getAccountKey() + " is not an USD account");
        }
    }

    public double getBalanceUSD() {
        // this should not be Optional since we check the underlying account
        // to have an USD position in the constructor
        return getPosition(POSITION_KEY).get().getQuantity();
    }

    @Override
    public String toString() {
        return "TweeAccountImpl{" + getAccountKey() +
                ", version " + getVersion() +
                ", balance usd " + getBalanceUSD() +
                "}";
    }
}
