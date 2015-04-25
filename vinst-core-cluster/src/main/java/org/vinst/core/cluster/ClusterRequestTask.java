package org.vinst.core.cluster;

import org.vinst.core.Request;
import org.vinst.core.Response;

import java.util.concurrent.Callable;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public final class ClusterRequestTask<RESP extends Response> implements Callable<RESP> {
    private final RequestService requestService;
    private final Request<RESP> request;

    public <REQ extends Request<RESP>> ClusterRequestTask(RequestService requestService, Request<RESP> request) {
        this.requestService = requestService;
        this.request = request;
    }

    public Request<RESP> getRequest() {
        return request;
    }

    @Override
    public RESP call() throws Exception {
        return requestService.process(request);
    }
}
