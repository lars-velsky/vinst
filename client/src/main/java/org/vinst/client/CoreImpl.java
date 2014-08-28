package org.vinst.client;

import com.hazelcast.client.HazelcastClient;

/**
 * @author lars-velsky
 * @since 28/08/14
 */
public class CoreImpl {

    public void init(){
        HazelcastClient.newHazelcastClient();
    }

    public static void main(String[] args) {
        new CoreImpl().init();
    }
}
