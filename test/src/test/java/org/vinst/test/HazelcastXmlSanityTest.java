package org.vinst.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Lars Velsky
 * @since 12/12/14
 */
public class HazelcastXmlSanityTest {

    Logger log = LoggerFactory.getLogger(HazelcastXmlSanityTest.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext serverContext = new ClassPathXmlApplicationContext("test-server-context.xml");
        ClassPathXmlApplicationContext clientContext = new ClassPathXmlApplicationContext("test-client-context.xml");
    }
}
