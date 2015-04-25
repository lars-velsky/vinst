package org.vinst.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public class RequestKeyResolverImpl implements RequestKeyResolver {

    private final Map<Class, RequestKeyProvider> requestKeyProviderMap;

    public RequestKeyResolverImpl(Collection<RequestKeyProvider> requestKeyProviders) {
        this.requestKeyProviderMap = new HashMap<>();
        for (RequestKeyProvider provider : requestKeyProviders) {
            RequestKeyProvider previous = requestKeyProviderMap.put(provider.getRequestClass(), provider);
            if (previous != null) {
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public Object getKey(Request request) {
        return requestKeyProviderMap.get(request.getClass()).getKey();
    }
}
