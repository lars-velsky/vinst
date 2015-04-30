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

    private RequestProcessingService requestProcessingService;
    private volatile HazelcastInstance hazelcast;

    @Autowired
    public void setRequestProcessingService(RequestProcessingService requestProcessingService) {
        this.requestProcessingService = requestProcessingService;
    }

    public void start() {
        Config config = new Config();

        SerializerConfig serializerConfig = new SerializerConfig();
        serializerConfig.setImplementation(new ClusterRequestProcessingTaskSerializer(requestProcessingService));
        serializerConfig.setTypeClass(ClusterRequestProcessingTask.class);
        config.getSerializationConfig().addSerializerConfig(serializerConfig);

        this.hazelcast = Hazelcast.newHazelcastInstance(config);
    }
}
