package org.vinst.client;

import org.vinst.core.requests.CoreRequest;
import org.vinst.core.requests.CoreResponse;

import java.util.concurrent.CompletableFuture;

/**
 * @author Lars Velsky
 * @since 22/09/14
 */
public interface RequestProcessor<REQ extends CoreRequest<RESP>, RESP extends CoreResponse> {

    Class<REQ> getRequestClass();

    CompletableFuture<RESP> processRequest(REQ request);
}
