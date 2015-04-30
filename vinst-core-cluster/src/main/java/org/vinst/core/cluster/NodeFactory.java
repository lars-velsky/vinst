package org.vinst.core.cluster;

import java.util.Properties;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public final class NodeFactory {

    private NodeFactory() {
    }

    public static Node createNode(Properties properties) {
        Node node = new Node(properties);
        node.start();
        return node;
    }
}
