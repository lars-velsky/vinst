package org.vinst.event;

import org.vinst.account.AccountKey;

/**
 * @author lars-velsky
 * @since 12/08/14
 */
public interface AccountEvent extends Event {

    AccountKey getAccountKey();
}
