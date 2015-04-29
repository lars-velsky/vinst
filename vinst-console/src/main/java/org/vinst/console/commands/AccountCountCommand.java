package org.vinst.console.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;
import org.vinst.core.AccountCountRequest;
import org.vinst.core.AccountCountResponse;
import org.vinst.core.Callback;
import org.vinst.core.Core;

import java.util.concurrent.CompletableFuture;

/**
 * @author Sergey Mischenko
 * @since 30.04.2015
 */
@Component
public class AccountCountCommand implements CommandMarker {

    @Autowired
    private Core core;

    @CliCommand(value = "show-account-count", help = "Show account count")
    public String showAccountCount() {
        CompletableFuture<AccountCountResponse> future = new CompletableFuture<>();
        core.process(new AccountCountRequest(), new Callback<AccountCountResponse>() {
            @Override
            public void response(AccountCountResponse response) {
                future.complete(response);
            }

            @Override
            public void error(Throwable throwable) {
                future.completeExceptionally(throwable);
            }
        }, Runnable::run);
        try {
            AccountCountResponse response = future.get();
            return "Accounts: " + response.getAccountCount();
        } catch (Exception e) {
            return "Error: " + e;
        }
    }
}
