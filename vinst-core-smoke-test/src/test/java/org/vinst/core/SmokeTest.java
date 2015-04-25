package org.vinst.core;

import org.junit.Assert;
import org.junit.Test;
import org.vinst.core.cluster.Node;
import org.vinst.core.cluster.NodeFactory;

import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public class SmokeTest {

    @Test
    public void test() throws ExecutionException, InterruptedException {
        Properties nodeProperties = new Properties();
        Node node = NodeFactory.createNode(nodeProperties);

        Properties coreProperties = new Properties();
        Core core = CoreFactory.createCore(coreProperties);

        final CompletableFuture<AccountCreateResponse> future = new CompletableFuture<>();
        core.process(new AccountCreateRequest("test"),
                new Callback<AccountCreateResponse>() {
                    @Override
                    public void response(AccountCreateResponse response) {
                        future.complete(response);
                    }

                    @Override
                    public void error(Throwable throwable) {
                        future.completeExceptionally(throwable);
                    }
                },
                Executors.newSingleThreadExecutor()
        );

        AccountCreateResponse response = future.get();
        Assert.assertNotNull(response);
    }
}
