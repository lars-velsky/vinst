package org.vinst.client.request;

import org.vinst.core.requests.CoreRequest;
import org.vinst.core.requests.CoreResponse;

import java.util.concurrent.Callable;

/**
 * @author Lars Velsky
 * @since 14/09/14
 */
public final class RequestTask<REQ extends CoreRequest<RESP>, RESP extends CoreResponse> implements Callable<RESP> {

    final REQ request;

    public RequestTask(REQ request) {
        this.request = request;
    }

    @Override
    public RESP call() throws Exception {
        throw new UnsupportedOperationException();
    }

}
