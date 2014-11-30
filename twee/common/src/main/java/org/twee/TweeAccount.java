package org.twee;

import org.vinst.account.Account;
import org.vinst.position.PositionKey;

import java.io.Serializable;

/**
 * @author Lars Velsky
 * @since 14/11/14
 */
public final class TweeAccount implements Serializable {

    public static final PositionKey POSITION_KEY = PositionKey.of(USD.KEY);
    private final Account account;

    public TweeAccount(Account account) {
        if (!account.getPositions().containsKey(POSITION_KEY)) {
            throw new IllegalArgumentException("Account " + account.getAccountKey() + " is not an USD account");
        }

        this.account = account;
    }

    public double getBalanceUSD() {
        // this should not be Optional since we check the underlying account
        // to have an USD position in the constructor
        return account.getPosition(POSITION_KEY).get().getQuantity();
    }

    @Override
    public String toString() {
        return "TweeAccount #" + account.getAccountKey().getId() +
                " v." + account.getVersion() +
                " balance " + getBalanceUSD() + " USD";
    }
}
