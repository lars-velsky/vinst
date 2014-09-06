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
public class HistoryFileNameProvider implements org.springframework.shell.plugin.HistoryFileNameProvider {
    @Override
    public String getHistoryFileName() {
        return "vinst-console-history.log";
    }

    @Override
    public String getProviderName() {
        return "Vinst history file name provider";
    }
}
