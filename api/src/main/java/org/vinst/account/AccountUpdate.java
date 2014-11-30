package org.vinst.account;

import org.vinst.event.AccountEvent;

import java.util.List;

/**
 * @author Lars Velsky
 * @since 12/08/14
 */
public interface AccountUpdate {

    AccountUpdateKey getAccountUpdateKey();

    List<AccountEvent> getEvents();
}
