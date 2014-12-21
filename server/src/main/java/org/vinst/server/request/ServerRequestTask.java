package org.vinst.server.request;

import org.vinst.core.requests.CoreRequest;
import org.vinst.core.requests.CoreResponse;

import java.util.concurrent.Callable;

/**
 * @author Lars Velsky
 * @since 15/09/14
 */
public final class ServerRequestTask implements Callable<CoreResponse> {

    private final RequestProcessorsRegistry requestProcessorsRegistry;
    private final CoreRequest request;

    public ServerRequestTask(RequestProcessorsRegistry requestProcessorsRegistry, CoreRequest coreRequest) {
        this.requestProcessorsRegistry = requestProcessorsRegistry;
        this.request = coreRequest;
    }

    @Override
    public CoreResponse call() throws Exception {
        return requestProcessorsRegistry.getRequestProcessor(request).processRequest(request);
    }

    public CoreRequest getRequest() {
        return request;
    }

}
