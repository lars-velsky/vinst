package org.vinst.core;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public class AccountCreateRequestKeyProvider implements RequestKeyProvider<AccountCreateRequest> {

    @Override
    public Class<AccountCreateRequest> getRequestClass() {
        return AccountCreateRequest.class;
    }

    @Override
    public Object getKey() {
        return null;
    }
}
