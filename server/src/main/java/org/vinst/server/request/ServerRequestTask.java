package org.vinst.server.request;

import org.vinst.core.requests.CoreRequest;
import org.vinst.core.requests.CoreResponse;

import java.util.concurrent.Callable;

/**
 * @author lars-velsky
 * @since 15/09/14
 */
public class ServerRequestTask implements Callable<CoreResponse> {

    private RequestsProcessor requestsProcessor;
    CoreRequest request;

    public ServerRequestTask(RequestsProcessor requestsProcessor, CoreRequest coreRequest) {
        this.requestsProcessor = requestsProcessor;
        this.request = coreRequest;
    }

    @Override
    public CoreResponse call() throws Exception {
        return requestsProcessor.process(request);
    }
}
