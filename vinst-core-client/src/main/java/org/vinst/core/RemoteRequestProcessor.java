package org.vinst.core;

import com.hazelcast.core.ExecutionCallback;
import com.hazelcast.core.IExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vinst.core.cluster.RequestProcessingResult;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public class RemoteRequestProcessor {
    private final static Logger logger = LoggerFactory.getLogger(RemoteRequestProcessor.class);
    private final IExecutorService requestExecutorService;
    private final RequestKeyResolver requestKeyResolver;

    public RemoteRequestProcessor(IExecutorService requestExecutorService, RequestKeyResolver requestKeyResolver) {
        this.requestExecutorService = requestExecutorService;
        this.requestKeyResolver = requestKeyResolver;
    }

    public <REQ extends Request<RESP>, RESP extends Response> void process(REQ request, Callback<RESP> callback, Executor executor) {
        Object key = requestKeyResolver.getKey(request);
        Callable<RequestProcessingResult<RESP>> task = new ClientRequestProcessingTask<>(request);

        ExecutionCallback<RequestProcessingResult<RESP>> executionCallback = new ExecutionCallback<RequestProcessingResult<RESP>>() {
            @Override
            public void onResponse(RequestProcessingResult<RESP> requestProcessingResult) {
                if (requestProcessingResult.hasResponse()) {
                    executor.execute(() -> callback.response(requestProcessingResult.getResponse()));
                } else {
                    logger.error("Cluster error while processing request {}", request);
                    executor.execute(() -> callback.error(new ClusterRequestProcessingError()));
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                logger.error("Error while processing request {}", request, throwable);
                executor.execute(() -> callback.error(throwable));
            }
        };

        if (key != null) {
            requestExecutorService.submitToKeyOwner(task, key, executionCallback);
        } else {
            requestExecutorService.submit(task, executionCallback);
        }
    }
}
