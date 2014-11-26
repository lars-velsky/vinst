package org.twee.console;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Sergey Mischenko
 * @since 06.09.2014
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BannerProvider implements org.springframework.shell.plugin.BannerProvider {
    @Override
    public String getBanner() {
       return " _                           __        _           _\n" +
              "| |                         / /       (_)         | |\n" +
              "| |___      _____  ___     / /  __   ___ _ __  ___| |_\n" +
              "| __\\ \\ /\\ / / _ \\/ _ \\   / /   \\ \\ / / | '_ \\/ __| __|\n" +
              "| |_ \\ V  V /  __/  __/  / /     \\ V /| | | | \\__ \\ |_\n" +
              " \\__| \\_/\\_/ \\___|\\___| /_/       \\_/ |_|_| |_|___/\\__|\n" +
              "                                                                 ";
    }

    @Override
    public String getVersion() {
        return "UNKNOWN";
    }

    @Override
    public String getWelcomeMessage() {
        return "Welcome to twee console. For assistance type \"help\" and press ENTER";
    }

    @Override
    public String getProviderName() {
        return "Twee console";
    }
}
