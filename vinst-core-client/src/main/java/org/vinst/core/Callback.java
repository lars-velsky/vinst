package org.vinst.core;

/**
 * @author Sergey Mischenko
 * @since 24.04.2015
 */
public interface Callback<R extends Response> {
    void response(R response);

    void error(Throwable throwable);
}
