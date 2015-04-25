package org.vinst.core.cluster;

import com.hazelcast.config.Config;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
@Configuration
public class NodeConfiguration {

    @Autowired
    public Collection<RequestProcessor> requestProcessors;

    @Bean
    public HazelcastInstance hazelcast() {
        Config config = new Config();

        SerializerConfig serializerConfig = new SerializerConfig();
        serializerConfig.setImplementation(clusterRequestTaskSerializer());
        serializerConfig.setTypeClass(ClusterRequestTask.class);
        config.getSerializationConfig().addSerializerConfig(serializerConfig);

        return Hazelcast.newHazelcastInstance(config);
    }

    @Bean
    public ClusterRequestTaskSerializer clusterRequestTaskSerializer() {
        return new ClusterRequestTaskSerializer();
    }

    @Bean
    public RequestService requestService() {
        return new RequestServiceImpl(requestProcessors);
    }

    @Bean
    public AccountCreateRequestProcessor accountCreateRequestProcessor() {
        return new AccountCreateRequestProcessor();
    }
}
