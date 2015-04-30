package org.vinst.core;

import org.vinst.core.cluster.RequestProcessingResult;

import java.util.concurrent.Callable;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public final class ClientRequestProcessingTask<RESP extends Response> implements Callable<RequestProcessingResult<RESP>> {
    private final Request<RESP> request;

    public ClientRequestProcessingTask(Request<RESP> request) {
        this.request = request;
    }

    public Request<RESP> getRequest() {
        return request;
    }

    @Override
    public RequestProcessingResult<RESP> call() throws Exception {
        throw new AssertionError("Method call() of ClientRequestTask must never be invoked");
    }
}
