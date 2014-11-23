package org.twee.console.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;
import org.vinst.core.Core;
import org.vinst.core.requests.GetAccountKeysRequest;
import org.vinst.core.requests.GetAccountKeysResponse;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * <p>Command that prints keys accounts.</p>
 *
 * @author Lars Velsky
 * @since 14/09/14
 */
@Component
public class GetAccountKeysCommand implements CommandMarker {

    @Autowired
    private Core core;

    @CliCommand(value = "get account keys", help = "Prints all account keys")
    public String getAccountKeys() throws ExecutionException, InterruptedException {
        CompletableFuture<GetAccountKeysResponse> future = core.process(new GetAccountKeysRequest());
        GetAccountKeysResponse createAccountResponse = future.get();
        return "" + createAccountResponse.getAccountKeys();
    }
}
