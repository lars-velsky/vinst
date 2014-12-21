package org.vinst.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.vinst.account.AccountKey;
import org.vinst.core.Core;
import org.vinst.core.requests.CreatePlainAccountRequest;
import org.vinst.core.requests.CreatePlainAccountResponse;
import org.vinst.core.requests.GetAccountKeysRequest;
import org.vinst.core.requests.GetAccountKeysResponse;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Lars Velsky
 * @since 21/12/14
 */
public class CreateAccountTest {

    private Core core;

    @Before
    public void setupTest(){
        new ClassPathXmlApplicationContext("test-server-context.xml");
        ClassPathXmlApplicationContext clientContext = new ClassPathXmlApplicationContext("test-client-context.xml");
        core = clientContext.getBean(Core.class);
    }

    @Test
    public void createAccountTest() throws ExecutionException, InterruptedException {
        CompletableFuture<CreatePlainAccountResponse> createAccountFuture = core.process(new CreatePlainAccountRequest());
        CreatePlainAccountResponse createAccountResponse = createAccountFuture.get();

        Assert.assertNotNull("Null create account response", createAccountResponse);

        AccountKey accountKey = createAccountResponse.getAccountKey();

        Assert.assertNotNull("Null account key in response", accountKey);

        CompletableFuture<GetAccountKeysResponse> getKeysFuture = core.process(new GetAccountKeysRequest());
        GetAccountKeysResponse getAccountKeysResponse = getKeysFuture.get();

        Assert.assertNotNull("Null get account keys response", getAccountKeysResponse);

        Set<AccountKey> accountKeys = getAccountKeysResponse.getAccountKeys();

        Assert.assertNotNull("Null account keys set in response", accountKeys);
        Assert.assertEquals("Account keys set size is not 1", 1, accountKeys.size());

        Assert.assertTrue("Account keys set from response doesn't contain created account key", accountKeys.contains(accountKey));



    }
}
