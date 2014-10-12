package org.vinst.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lars-velsky
 * @since 31/07/14
 */
public class Launcher {

    private static final Logger log = LoggerFactory.getLogger(Launcher.class);

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("server.xml");
        log.info("Context started");
    }

}
