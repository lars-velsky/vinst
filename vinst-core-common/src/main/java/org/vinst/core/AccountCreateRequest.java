package org.vinst.core;

import java.io.Serializable;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public final class AccountCreateRequest implements Request<AccountCreateResponse>, Serializable {
    private final String name;

    public AccountCreateRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
