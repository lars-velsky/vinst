package org.vinst.core.cluster;

import org.springframework.context.ApplicationContext;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public final class Node {
    private final ApplicationContext context;

    public Node(ApplicationContext context) {
        this.context = context;
    }
}
