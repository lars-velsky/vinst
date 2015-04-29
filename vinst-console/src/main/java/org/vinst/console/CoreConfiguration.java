package org.vinst.console;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vinst.core.Core;
import org.vinst.core.CoreFactory;

import java.util.Properties;

/**
 * @author Sergey Mischenko
 * @since 30.04.2015
 */
@Configuration
public class CoreConfiguration {

    @Bean
    public Core core() {
        return CoreFactory.createCore(new Properties());
    }
}
