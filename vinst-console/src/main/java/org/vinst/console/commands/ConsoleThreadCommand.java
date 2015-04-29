package org.vinst.console.commands;

import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;

/**
 * @author Sergey Mischenko
 * @since 30.04.2015
 */
@Component
public class ConsoleThreadCommand implements CommandMarker {

    @CliCommand(value = "show-thread", help = "Show console thread name")
    public String showThread() {
        return Thread.currentThread().getName();
    }
}
