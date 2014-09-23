package org.vinst.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vinst.client.request.RequestProcessor;
import org.vinst.client.request.RequestProcessorsRegistry;
import org.vinst.core.Core;
import org.vinst.core.requests.CoreRequest;
import org.vinst.core.requests.CoreResponse;

import java.util.concurrent.CompletableFuture;

/**
 * @author lars-velsky
 * @since 28/08/14
 */
@Service
public class CoreImpl implements Core {

    @Autowired
    private RequestProcessorsRegistry processorsRegistry;

    @Override
    public <REQ extends CoreRequest<RESP>, RESP extends CoreResponse> CompletableFuture<RESP> process(REQ request) {
        RequestProcessor<REQ, RESP> requestProcessor = processorsRegistry.getRequestProcessor(request);
        return requestProcessor.processRequest(request);
    }
}
