package org.vinst.core.cluster;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public final class Node {
    private final ApplicationContext context;

    public Node(Properties properties) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(NodeConfiguration.class);
        MutablePropertySources propertySources = ctx.getEnvironment().getPropertySources();
        propertySources.addFirst(new PropertiesPropertySource("node", properties));
        ctx.refresh();

        this.context = ctx;
    }

    public void start() {
        HazelcastService hazelcastService = context.getBean(HazelcastService.class);
        hazelcastService.start();
    }
}
