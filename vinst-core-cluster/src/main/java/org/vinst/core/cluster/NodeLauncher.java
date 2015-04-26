package org.vinst.core.cluster;

import java.util.Properties;

/**
 * @author Sergey Mischenko
 * @since 26.04.2015
 */
public final class NodeLauncher {

    public static void main(String[] args) {
        Properties properties = new Properties();
        Node node = NodeFactory.createNode(properties);
    }

    private NodeLauncher() {
    }
}
