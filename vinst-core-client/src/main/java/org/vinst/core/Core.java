package org.vinst.core;

import java.util.concurrent.Executor;

/**
 * Facade of system core.
 *
 * @author Sergey Mischenko
 * @since 24.04.2015
 */
public interface Core {

    <REQ extends Request<RESP>, RESP extends Response> void process(REQ request, Callback<RESP> callback, Executor executor);

    Subscription subscribe(SubscriptionManager manager, Executor executor);
}
