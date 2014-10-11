package org.vinst.client.request.processors;

import com.hazelcast.core.ExecutionCallback;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vinst.client.request.RequestProcessor;
import org.vinst.client.request.RequestTask;
import org.vinst.common.Constants;
import org.vinst.core.requests.GetAccountKeysRequest;
import org.vinst.core.requests.GetAccountKeysResponse;

import java.util.concurrent.CompletableFuture;

/**
 * @author Lars Velsky
 * @since 22/09/14
 */
@Component
public class GetAccountKeysRequestProcessor implements RequestProcessor<GetAccountKeysRequest, GetAccountKeysResponse> {

    @Autowired
    private HazelcastInstance hzClient;

    @Override
    public Class<GetAccountKeysRequest> getRequestClass() {
        return GetAccountKeysRequest.class;
    }

    @Override
    public CompletableFuture<GetAccountKeysResponse> processRequest(GetAccountKeysRequest request) {
        CompletableFuture<GetAccountKeysResponse> future = new CompletableFuture<>();
        IExecutorService executorService = hzClient.getExecutorService(Constants.REQUESTS_EXECUTOR);
        RequestTask<GetAccountKeysRequest, GetAccountKeysResponse> task = new RequestTask<>(request);
        executorService.submit(task, new ExecutionCallback<GetAccountKeysResponse>() {
            @Override
            public void onResponse(GetAccountKeysResponse response) {
                future.complete(response);
            }

            @Override
            public void onFailure(Throwable t) {
                future.completeExceptionally(t);
            }
        });
        return future;
    }
}
