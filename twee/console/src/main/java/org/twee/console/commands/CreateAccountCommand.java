package org.twee.console.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;
import org.twee.CreateAccountRequest;
import org.twee.CreateAccountResponse;
import org.vinst.core.Core;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * <p>Command for creating new accounts.</p>
 *
 * <p>For now takes no arguments and just creates a
 * new account with USD as a currency.</p>
 *
 * @author Lars Velsky
 * @since 14/09/14
 */
@Component
public class CreateAccountCommand implements CommandMarker {

    @Autowired
    private Core core;

    @CliCommand(value = "create usd account", help = "Creates new account")
    public String createAccount() throws ExecutionException, InterruptedException {
        CompletableFuture<CreateAccountResponse> future = core.process(new CreateAccountRequest());
        CreateAccountResponse createAccountResponse = future.get();
        return "Account created: " + createAccountResponse.getAccountKey().toString();
    }
}
