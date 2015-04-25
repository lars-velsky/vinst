package org.vinst.core.cluster;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Properties;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public final class NodeFactory {

    private NodeFactory() {
    }

    public static Node createNode(Properties properties) {
        ApplicationContext context = new AnnotationConfigApplicationContext(NodeConfiguration.class);
        return new Node(context);
    }
}
