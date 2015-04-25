package org.vinst.core;

import java.util.concurrent.Callable;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public final class ClientRequestTask<RESP extends Response> implements Callable<RESP> {
    private final Request<RESP> request;

    public <REQ extends Request<RESP>> ClientRequestTask(Request<RESP> request) {
        this.request = request;
    }

    public Request<RESP> getRequest() {
        return request;
    }

    @Override
    public RESP call() throws Exception {
        throw new AssertionError("Method call() of ClientRequestTask must never be invoked");
    }
}
