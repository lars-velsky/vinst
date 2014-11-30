package org.twee.console.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;
import org.twee.USDTransferRequest;
import org.twee.USDTransferResponse;
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
            long accountId,
            @CliOption(key = { "amount" }, mandatory = true, help = "USD amount")
            double amount
    ) throws ExecutionException, InterruptedException {
        CompletableFuture<USDTransferResponse> future = core.process(new USDTransferRequest(AccountKey.of(accountId), amount));
        USDTransferResponse getAccountResponse = future.get();
        return "done";
    }


}
