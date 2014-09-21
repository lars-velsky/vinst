package org.vinst.account;

import org.vinst.event.AccountEvent;
import org.vinst.event.Event;

import java.util.List;

/**
 * @author lars-velsky
 * @since 12/08/14
 */
public interface AccountUpdate {

    AccountUpdateKey getAccountUpdateKey();

    List<AccountEvent> getEvents();
}
