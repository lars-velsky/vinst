package org.vinst.core;

import java.util.Properties;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public final class CoreFactory {

    private CoreFactory() {
    }

    public static Core createCore(Properties properties) {
        return new CoreImpl(properties);
    }
}
