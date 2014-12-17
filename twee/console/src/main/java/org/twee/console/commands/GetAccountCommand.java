package org.twee.console.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;
import org.twee.GetAccountRequest;
import org.twee.GetAccountResponse;
import org.twee.Utils;
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

    @CliCommand(value = "get-account", help = "Fetches a account")
    public String getAccount(
            @CliOption(key = { "id" }, mandatory = true, help = "Account ID")
            String idString
    ) throws ExecutionException, InterruptedException {
        AccountKey accountKey;
        try {
            accountKey = Utils.getAccountKey(idString);
        } catch (NumberFormatException e){
            return "Invalid account id: " + idString;
        }
        CompletableFuture<GetAccountResponse> future = core.process(new GetAccountRequest(accountKey));
        GetAccountResponse getAccountResponse;
        try {
            getAccountResponse = future.get();
        } catch (Throwable e){
            while (e.getCause() != null){
                e = e.getCause();
            }
            return e.getMessage();
        }
        return getAccountResponse.getAccount().toString();
    }

}
