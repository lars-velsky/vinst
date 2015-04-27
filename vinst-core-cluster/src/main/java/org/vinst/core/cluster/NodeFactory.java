package org.vinst.core.cluster;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public final class NodeFactory {

    private NodeFactory() {
    }

    public static Node createNode(Properties properties) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(NodeConfiguration.class);
        MutablePropertySources propertySources = context.getEnvironment().getPropertySources();
        propertySources.addFirst(new PropertiesPropertySource("node", properties));
        context.refresh();
        return new Node(context);
    }
}
