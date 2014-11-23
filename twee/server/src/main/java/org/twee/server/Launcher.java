package org.twee.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Lars Velsky
 * @since 31/07/14
 */
public class Launcher {

    private static final Logger log = LoggerFactory.getLogger(Launcher.class);

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("twee-server-context.xml");
        log.info("Context started");
    }

}
