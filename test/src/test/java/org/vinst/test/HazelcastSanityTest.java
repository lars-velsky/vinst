package org.vinst.test;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.Hazelcast;

/**
 * @author Lars Velsky
 * @since 12/12/14
 */
public class HazelcastSanityTest {

    public static void main(String[] args) {
        Hazelcast.newHazelcastInstance();

        HazelcastClient.newHazelcastClient();
    }
}
