package org.vinst.core.cluster;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vinst.core.Request;
import org.vinst.core.Response;

import java.util.concurrent.Callable;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public final class ClusterRequestProcessingTask implements Callable<RequestProcessingResult> {
    private static final Logger logger = LoggerFactory.getLogger(ClusterRequestProcessingTask.class);
    private final RequestProcessingService requestProcessingService;
    private final Request request;

    public ClusterRequestProcessingTask(RequestProcessingService requestProcessingService, Request request) {
        this.requestProcessingService = requestProcessingService;
        this.request = request;
    }

    public Request getRequest() {
        return request;
    }

    @Override
    public RequestProcessingResult call() throws Exception {
        try {
            Response response = requestProcessingService.process(request);
            return RequestProcessingResult.response(response);
        } catch (Throwable throwable) {
            logger.error("Error while processing request {}", request, throwable);
            return RequestProcessingResult.error();
        }
    }
}
