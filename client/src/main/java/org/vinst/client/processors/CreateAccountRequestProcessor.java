package org.vinst.client.processors;

import com.hazelcast.core.ExecutionCallback;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.vinst.client.RequestProcessor;
import org.vinst.client.RequestTask;
import org.vinst.common.Constants;
import org.vinst.core.requests.CreateAccountRequest;
import org.vinst.core.requests.CreateAccountResponse;

import java.util.concurrent.CompletableFuture;

/**
 * @author Lars Velsky
 * @since 22/09/14
 */
public class CreateAccountRequestProcessor implements RequestProcessor<CreateAccountRequest, CreateAccountResponse> {

    @Autowired
    private HazelcastInstance hzClient;

    @Override
    public Class<CreateAccountRequest> getRequestClass() {
        return CreateAccountRequest.class;
    }

    @Override
    public CompletableFuture<CreateAccountResponse> processRequest(CreateAccountRequest request) {
        CompletableFuture<CreateAccountResponse> future = new CompletableFuture<>();
        IExecutorService executorService = hzClient.getExecutorService(Constants.REQUESTS_EXECUTOR);
        RequestTask<CreateAccountRequest, CreateAccountResponse> task = new RequestTask<>(request);
        executorService.submit(task, new ExecutionCallback<CreateAccountResponse>() {
            @Override
            public void onResponse(CreateAccountResponse response) {
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
