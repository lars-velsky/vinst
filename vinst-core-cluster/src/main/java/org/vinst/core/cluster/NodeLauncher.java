package org.vinst.core.cluster;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Sergey Mischenko
 * @since 26.04.2015
 */
public final class NodeLauncher {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Path to node properties file is expected as argument");
        }

        Properties properties = new Properties();
        properties.load(new FileReader(args[0]));

        Node node = NodeFactory.createNode(properties);
    }

    private NodeLauncher() {
    }
}
