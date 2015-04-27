package org.vinst.core;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.vinst.core.cluster.Node;
import org.vinst.core.cluster.NodeConfiguration;
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
        // Create database
        new EmbeddedDatabaseBuilder().
                setType(EmbeddedDatabaseType.H2).
                setName("test").
                addScript("schema.sql").
                build();

        // Create cluster node
        Properties nodeProperties = new Properties();
        nodeProperties.setProperty(NodeConfiguration.DATABASE_DRIVER_CLASS_NAME, "org.h2.Driver");
        nodeProperties.setProperty(NodeConfiguration.DATABASE_URL, "jdbc:h2:mem:test");
        nodeProperties.setProperty(NodeConfiguration.DATABASE_USERNAME, "sa");
        nodeProperties.setProperty(NodeConfiguration.DATABASE_PASSWORD, "");
        Node node = NodeFactory.createNode(nodeProperties);

        // Create core
        Properties coreProperties = new Properties();
        Core core = CoreFactory.createCore(coreProperties);

        // Perform core request
        final CompletableFuture<AccountCountResponse> future = new CompletableFuture<>();
        core.process(new AccountCountRequest(),
                new Callback<AccountCountResponse>() {
                    @Override
                    public void response(AccountCountResponse response) {
                        future.complete(response);
                    }

                    @Override
                    public void error(Throwable throwable) {
                        future.completeExceptionally(throwable);
                    }
                },
                Executors.newSingleThreadExecutor()
        );

        AccountCountResponse response = future.get();
        Assert.assertEquals(0, response.getAccountCount());
    }
}
