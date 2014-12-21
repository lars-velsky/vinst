package org.vinst.core;

import org.vinst.core.requests.CoreRequest;
import org.vinst.core.requests.CoreResponse;

import java.util.concurrent.CompletableFuture;

/**
 * <p>System's facade.</p>
 *
 * @author Sergey Mischenko
 * @since 27.08.2014
 */
public interface Core {

    <REQ extends CoreRequest<RESP>, RESP extends CoreResponse> CompletableFuture<RESP> process(REQ request);
}
