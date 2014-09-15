package org.vinst.client;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.config.ClasspathXmlConfig;
import com.hazelcast.core.ExecutionCallback;
import com.hazelcast.core.IExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.vinst.common.Constants;
import org.vinst.core.Core;
import org.vinst.core.requests.CoreRequest;
import org.vinst.core.requests.CoreResponse;

import java.util.concurrent.CompletableFuture;

/**
 * @author lars-velsky
 * @since 28/08/14
 */
@Service
public class CoreImpl implements Core {

    @Autowired
    private HazelcastClient hzClient;

    public static void main(String[] args) {
        // todo
        System.out.println("loaded");
    }

    @Override
    public <REQ extends CoreRequest<RESP>, RESP extends CoreResponse> CompletableFuture<RESP> process(REQ request) {
        IExecutorService executorService = hzClient.getExecutorService(Constants.REQUESTS_EXECUTOR);

        CompletableFuture<RESP> future = new CompletableFuture<>();
        executorService.submit(new RequestTask<>(request), new ExecutionCallback<RESP>() {
            @Override
            public void onResponse(RESP response) {
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
