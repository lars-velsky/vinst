package org.vinst.core.cluster;

import com.hazelcast.config.Config;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Sergey Mischenko
 * @since 30.04.2015
 */
public class HazelcastService {

    private RequestService requestService;
    private volatile HazelcastInstance hazelcast;

    @Autowired
    public void setRequestService(RequestService requestService) {
        this.requestService = requestService;
    }

    public void start() {
        Config config = new Config();

        SerializerConfig serializerConfig = new SerializerConfig();
        serializerConfig.setImplementation(new ClusterRequestTaskSerializer(requestService));
        serializerConfig.setTypeClass(ClusterRequestTask.class);
        config.getSerializationConfig().addSerializerConfig(serializerConfig);

        this.hazelcast = Hazelcast.newHazelcastInstance(config);
    }
}
