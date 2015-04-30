package org.vinst.core;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
@Configuration
public class CoreConfiguration {

    @Autowired
    public Collection<RequestKeyProvider> requestKeyProviders;

    @Bean
    public RemoteRequestProcessor remoteRequestProcessor() {
        return new RemoteRequestProcessor(
                requestExecutorService(),
                requestKeyResolver());
    }

    @Bean
    public IExecutorService requestExecutorService() {
        return hazelcast().getExecutorService("request-executor");
    }

    @Bean
    public HazelcastInstance hazelcast() {
        ClientConfig clientConfig = new ClientConfig();

        SerializerConfig serializerConfig = new SerializerConfig();
        serializerConfig.setImplementation(new ClientRequestTaskSerializer());
        serializerConfig.setTypeClass(ClientRequestProcessingTask.class);
        clientConfig.getSerializationConfig().addSerializerConfig(serializerConfig);

        return HazelcastClient.newHazelcastClient(clientConfig);
    }

    @Bean
    public RequestKeyResolver requestKeyResolver() {
        return new RequestKeyResolverImpl(requestKeyProviders);
    }

    @Bean
    public AccountCreateRequestKeyProvider accountCreateRequestKeyProvider() {
        return new AccountCreateRequestKeyProvider();
    }

    @Bean
    public AccountCountRequestKeyProvider accountCountRequestKeyProvider() {
        return new AccountCountRequestKeyProvider();
    }
}
