package org.vinst.core;

/**
 * @author Sergey Mischenko
 * @since 27.04.2015
 */
public class AccountCountRequestKeyProvider implements RequestKeyProvider<AccountCountRequest> {

    @Override
    public Class<AccountCountRequest> getRequestClass() {
        return AccountCountRequest.class;
    }

    @Override
    public Object getKey() {
        return null;
    }
}
