package org.vinst.core;

import java.io.Serializable;

/**
 * @author Sergey Mischenko
 * @since 27.04.2015
 */
public class AccountCountResponse implements Response, Serializable {
    private final int accountCount;

    public AccountCountResponse(int accountCount) {
        this.accountCount = accountCount;
    }

    public int getAccountCount() {
        return accountCount;
    }
}
