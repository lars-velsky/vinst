package org.vinst.server;

import com.hazelcast.config.Config;
import com.hazelcast.config.SerializationConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.vinst.common.Constants;
import org.vinst.server.request.RequestsProcessor;
import org.vinst.server.request.ServerRequestTaskFactory;

/**
 * @author lars-velsky
 * @since 31/07/14
 */
public class Launcher {


    public static void main(String[] args) {
        ServerRequestTaskFactory serverRequestTaskFactory = new ServerRequestTaskFactory(new RequestsProcessor());

        Config config = new Config();

        SerializationConfig serializationConfig = new SerializationConfig();
        serializationConfig.addDataSerializableFactory(Constants.REQUEST_TASK_FACTORY, serverRequestTaskFactory);
        config.setSerializationConfig(serializationConfig);

        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
    }

}
