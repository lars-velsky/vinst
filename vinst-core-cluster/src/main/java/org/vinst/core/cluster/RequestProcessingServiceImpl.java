package org.vinst.core.cluster;

import org.vinst.core.Request;
import org.vinst.core.Response;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public final class RequestProcessingServiceImpl implements RequestProcessingService {

    private final Map<Class, RequestProcessor> requestProcessorMap;

    public RequestProcessingServiceImpl(Collection<RequestProcessor> requestProcessors) {
        this.requestProcessorMap = new HashMap<>();
        for (RequestProcessor requestProcessor : requestProcessors) {
            RequestProcessor previous = requestProcessorMap.put(requestProcessor.getRequestClass(), requestProcessor);
            if (previous != null) {
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public Response process(Request request) {
        //noinspection unchecked
        return requestProcessorMap.get(request.getClass()).process(request);
    }
}
