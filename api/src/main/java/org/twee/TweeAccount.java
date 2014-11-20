package org.twee;

import org.vinst.account.Account;

/**
 * @author Lars Velsky
 * @since 14/11/14
 */
public interface TweeAccount extends Account {

    /**
     * <p>Returns amount of USD this account have.</p>
     *
     * @return USD balance
     */
    double getBalanceUSD();
}
