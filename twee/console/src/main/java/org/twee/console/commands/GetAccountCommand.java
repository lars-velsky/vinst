package org.twee.console.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;
import org.twee.GetAccountRequest;
import org.twee.GetAccountResponse;
import org.vinst.account.AccountKey;
import org.vinst.core.Core;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Lars Velsky
 * @since 19/11/14
 */
@Component
public class GetAccountCommand implements CommandMarker {

    @Autowired
    private Core core;

    @CliCommand(value = "get account", help = "Fetches a account")
    public String getAccount(
            @CliOption(key = { "id" }, mandatory = true, help = "Account id")
            long id
    ) throws ExecutionException, InterruptedException {
        CompletableFuture<GetAccountResponse> future = core.process(new GetAccountRequest(AccountKey.of(id)));
        GetAccountResponse getAccountResponse = future.get();
        return getAccountResponse.getAccount().toString();
    }

}
