package org.vinst.core;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public class AccountCreateRequestKeyProvider implements RequestKeyProvider<AccountCreateRequest> {

    private final AtomicInteger nextRequestKey = new AtomicInteger(0);

    @Override
    public Class<AccountCreateRequest> getRequestClass() {
        return AccountCreateRequest.class;
    }

    @Override
    public Object getKey() {
        return nextRequestKey.getAndIncrement();
    }
}
