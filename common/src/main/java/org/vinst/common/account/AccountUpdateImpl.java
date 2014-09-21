package org.vinst.common.account;

import org.vinst.account.AccountUpdate;
import org.vinst.account.AccountUpdateKey;
import org.vinst.event.Event;

import java.io.Serializable;
import java.util.List;

/**
 * @author lars-velsky
 * @since 21/09/14
 */
public final class AccountUpdateImpl implements AccountUpdate, Serializable {

    private final AccountUpdateKey accountKey;
    private final List<Event> events;

    public AccountUpdateImpl(AccountUpdateKey accountKey, List<Event> events) {
        this.accountKey = accountKey;
        this.events = events;
    }

    @Override
    public AccountUpdateKey getAccountUpdateKey() {
        return accountKey;
    }

    @Override
    public List<Event> getEvents() {
        return events;
    }
}
