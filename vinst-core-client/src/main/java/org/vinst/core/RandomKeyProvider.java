package org.vinst.core;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Sergey Mischenko
 * @since 27.04.2015
 */
public final class RandomKeyProvider {

    private final AtomicInteger nextRequestKey = new AtomicInteger(0);

    public Object getKey() {
        return nextRequestKey.getAndIncrement();
    }
}
