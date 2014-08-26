package org.vinst.console;

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
        return "        _           _   \n" +
                " __   _(_)_ __  ___| |_ \n" +
                " \\ \\ / / | '_ \\/ __| __|\n" +
                "  \\ V /| | | | \\__ \\ |_ \n" +
                "   \\_/ |_|_| |_|___/\\__|\n" +
                "                        ";
    }

    @Override
    public String getVersion() {
        return "UNKNOWN";
    }

    @Override
    public String getWelcomeMessage() {
        return "Welcome to vinst console. For assistance type \"help\" and press ENTER";
    }

    @Override
    public String getProviderName() {
        return "Vinst console";
    }
}