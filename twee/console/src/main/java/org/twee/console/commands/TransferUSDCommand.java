package org.twee.console.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;
import org.twee.USDTransferRequest;
import org.twee.USDTransferResponse;
import org.twee.Utils;
import org.vinst.account.AccountKey;
import org.vinst.core.Core;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Lars Velsky
 * @since 30/11/14
 */
@Component
public class TransferUSDCommand implements CommandMarker {

    @Autowired
    private Core core;

    @CliCommand(value = "transfer-usd", help = "Fetches a account")
    public String getAccount(
            @CliOption(key = { "account-id" }, mandatory = true, help = "Account ID")
            String accountIdString,
            @CliOption(key = { "amount" }, mandatory = true, help = "USD amount")
            double amount
    ) throws ExecutionException, InterruptedException {
        AccountKey accountKey;
        try {
            accountKey = Utils.getAccountKey(accountIdString);
        } catch (NumberFormatException e){
            return "Invalid account id: " + accountIdString;
        }

        CompletableFuture<USDTransferResponse> future = core.process(new USDTransferRequest(accountKey, amount));
        USDTransferResponse transferResponse;
        try {
            transferResponse = future.get();
        } catch (Throwable e){
            while (e.getCause() != null){
                e = e.getCause();
            }
            return e.getMessage();
        }
        return "New balance: " + Utils.formatUSDAmount(transferResponse.getResultingBalance()) + " USD";
    }


}
