package org.vinst.core;

import com.hazelcast.core.ExecutionCallback;
import com.hazelcast.core.IExecutorService;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public class RemoteRequestProcessor {

    private final IExecutorService requestExecutorService;
    private final RequestKeyResolver requestKeyResolver;

    public RemoteRequestProcessor(IExecutorService requestExecutorService, RequestKeyResolver requestKeyResolver) {
        this.requestExecutorService = requestExecutorService;
        this.requestKeyResolver = requestKeyResolver;
    }

    public <REQ extends Request<RESP>, RESP extends Response> void process(REQ request, Callback<RESP> callback, Executor executor) {
        Object key = requestKeyResolver.getKey(request);
        Callable<RESP> task = new ClientRequestTask<>(request);
        requestExecutorService.submitToKeyOwner(task, key, new ExecutionCallback<RESP>() {
            @Override
            public void onResponse(RESP response) {
                executor.execute(() -> callback.response(response));
            }

            @Override
            public void onFailure(Throwable throwable) {
                executor.execute(() -> callback.error(throwable));
            }
        });
    }
}
