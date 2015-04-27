package org.vinst.core;

/**
 * @author Sergey Mischenko
 * @since 27.04.2015
 */
public class AccountCountRequestKeyProvider implements RequestKeyProvider<AccountCountRequest> {

    private final RandomKeyProvider randomKeyProvider;

    public AccountCountRequestKeyProvider(RandomKeyProvider randomKeyProvider) {
        this.randomKeyProvider = randomKeyProvider;
    }

    @Override
    public Class<AccountCountRequest> getRequestClass() {
        return AccountCountRequest.class;
    }

    @Override
    public Object getKey() {
        return randomKeyProvider.getKey();
    }
}
