package org.twee.console.commands;

import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

/**
 * Echo console command. Just echoes the argument. It acts as an example of a console command
 *
 * @author Sergey Mischenko
 * @since 06.09.2014
 */
@Component
public class EchoCommand implements CommandMarker {

    @CliCommand(value = "echo", help = "Echo argument")
    public String echo(
            @CliOption(
                    key = {"message", "m"},
                    help = "Message to echo",
                    mandatory = true)
            String message) {
        return message;
    }
}
