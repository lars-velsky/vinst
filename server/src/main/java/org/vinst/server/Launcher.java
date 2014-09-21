package org.vinst.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lars-velsky
 * @since 31/07/14
 */
public class Launcher {


    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("server.xml");
    }

}
