package org.vinst.core;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public interface RequestKeyProvider<R extends Request> {

    Class<R> getRequestClass();

    Object getKey();
}
