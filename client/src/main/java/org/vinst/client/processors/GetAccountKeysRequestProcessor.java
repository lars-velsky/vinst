package org.vinst.client.processors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vinst.account.AccountKey;
import org.vinst.client.AccountCache;
import org.vinst.client.RequestProcessor;
import org.vinst.core.requests.GetAccountKeysRequest;
import org.vinst.core.requests.GetAccountKeysResponse;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * @author Lars Velsky
 * @since 22/09/14
 */
@Component
public class GetAccountKeysRequestProcessor implements RequestProcessor<GetAccountKeysRequest, GetAccountKeysResponse> {

    @Autowired
    private AccountCache accountCache;

    @Override
    public Class<GetAccountKeysRequest> getRequestClass() {
        return GetAccountKeysRequest.class;
    }

    @Override
    public CompletableFuture<GetAccountKeysResponse> processRequest(GetAccountKeysRequest request) {
        CompletableFuture<GetAccountKeysResponse> future = new CompletableFuture<>();
        Set<AccountKey> accountKeys = accountCache.getAccountKeys();
        GetAccountKeysResponse getAccountKeysResponse = new GetAccountKeysResponse(accountKeys);
        future.complete(getAccountKeysResponse);
        return future;
    }
}
