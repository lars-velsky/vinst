package org.vinst.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public final class CoreImpl implements Core {

    private final ApplicationContext context;

    public CoreImpl(Properties properties) {
        this.context = new AnnotationConfigApplicationContext(CoreConfiguration.class);
    }

    @Override
    public <REQ extends Request<RESP>, RESP extends Response> void process(REQ request, Callback<RESP> callback, Executor executor) {
        RemoteRequestProcessor requestProcessor = context.getBean(RemoteRequestProcessor.class);
        requestProcessor.process(request, callback, executor);
    }

    @Override
    public Subscription subscribe(SubscriptionManager manager, Executor executor) {
        // TODO
        throw new UnsupportedOperationException();
    }
}
