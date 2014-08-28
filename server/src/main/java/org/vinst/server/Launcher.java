package org.vinst.server;

import com.hazelcast.core.Hazelcast;

/**
 * @author lars-velsky
 * @since 31/07/14
 */
public class Launcher {

    public static void main(String[] args) {
        Hazelcast.newHazelcastInstance();
    }

}
