package org.vinst.core;

import org.springframework.context.ApplicationContext;

import java.util.concurrent.Executor;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public final class CoreImpl implements Core {

    private final ApplicationContext context;

    public CoreImpl(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public <REQ extends Request<RESP>, RESP extends Response> void process(REQ request, Callback<RESP> callback, Executor executor) {
        RemoteRequestProcessor requestProcessor = context.getBean(RemoteRequestProcessor.class);
        requestProcessor.process(request, callback, executor);
    }
}
