package org.vinst.core;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public interface RequestKeyResolver {
    Object getKey(Request request);
}
