package org.twee.common;

import org.twee.USD;
import org.twee.TweeAccount;
import org.vinst.account.Account;
import org.vinst.account.AccountDelegate;
import org.vinst.position.PositionKey;

import java.io.Serializable;

/**
 * @author Lars Velsky
 * @since 14/11/14
 */
public class TweeAccountImpl extends AccountDelegate implements TweeAccount, Serializable {

    public static final PositionKey POSITION_KEY = PositionKey.of(USD.KEY);

    public TweeAccountImpl(Account account) {
        super(account);

        if (!account.getPositions().containsKey(POSITION_KEY)) {
            throw new IllegalArgumentException("Account " + getAccountKey() + " is not an USD account");
        }
    }

    @Override
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
