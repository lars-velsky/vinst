package org.vinst.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Properties;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public final class CoreFactory {

    private CoreFactory() {
    }

    public static Core createCore(Properties properties) {
        ApplicationContext context = new AnnotationConfigApplicationContext(CoreConfiguration.class);
        return new CoreImpl(context);
    }
}
