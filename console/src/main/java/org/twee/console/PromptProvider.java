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
public class PromptProvider implements org.springframework.shell.plugin.PromptProvider {
    @Override
    public String getPrompt() {
        return "vinst>";
    }

    @Override
    public String getProviderName() {
        return "Vinst prompt provider";
    }
}
