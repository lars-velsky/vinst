package org.vinst.common.account;

import org.springframework.data.annotation.Id;
import org.vinst.account.AccountUpdate;
import org.vinst.account.AccountUpdateKey;
import org.vinst.event.AccountEvent;

import java.io.Serializable;
import java.util.List;

/**
 * @author lars-velsky
 * @since 21/09/14
 */
public final class AccountUpdateImpl implements AccountUpdate, Serializable {

    @Id
    private final AccountUpdateKey accountKey;
    private final List<AccountEvent> events;

    public AccountUpdateImpl(AccountUpdateKey accountKey, List<AccountEvent> events) {
        this.accountKey = accountKey;
        this.events = events;
    }

    @Override
    public AccountUpdateKey getAccountUpdateKey() {
        return accountKey;
    }

    @Override
    public List<AccountEvent> getEvents() {
        return events;
    }
}
