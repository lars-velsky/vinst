package org.vinst.client;

import com.hazelcast.core.ExecutionCallback;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vinst.account.AccountKey;
import org.vinst.common.Constants;
import org.vinst.core.Core;
import org.vinst.core.requests.CoreRequest;
import org.vinst.core.requests.CoreResponse;
import org.vinst.core.requests.GetAccountKeysRequest;
import org.vinst.core.requests.GetAccountKeysResponse;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * @author lars-velsky
 * @since 28/08/14
 */
@Service
public class CoreImpl implements Core {

    @Autowired
    private HazelcastInstance hzClient;

    @Autowired
    private AccountCache accountCache;

    @Override
    public <REQ extends CoreRequest<RESP>, RESP extends CoreResponse> CompletableFuture<RESP> process(REQ request) {
        CompletableFuture<RESP> future = new CompletableFuture<>();

        // todo looks stupid and unsafe
        if (request instanceof GetAccountKeysRequest){
            Set<AccountKey> accountKeys = accountCache.getAccountKeys();
            GetAccountKeysResponse getAccountKeysResponse = new GetAccountKeysResponse(accountKeys);
            future.complete((RESP) getAccountKeysResponse);
        } else {
            IExecutorService executorService = hzClient.getExecutorService(Constants.REQUESTS_EXECUTOR);
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
        }
        return future;
    }
}
