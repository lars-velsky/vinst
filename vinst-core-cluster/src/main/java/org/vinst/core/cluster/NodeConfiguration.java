package org.vinst.core.cluster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Collection;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
@Configuration
public class NodeConfiguration {

    public static final String DATABASE_DRIVER_CLASS_NAME = "database.driverClassName";
    public static final String DATABASE_URL = "database.url";
    public static final String DATABASE_USERNAME = "database.username";
    public static final String DATABASE_PASSWORD = "database.password";

    @Autowired
    public Environment environment;

    @Autowired
    public Collection<RequestProcessor> requestProcessors;

    @Bean
    public HazelcastService hazelcastService() {
        return new HazelcastService();
    }

    @Bean
    public RequestProcessingService requestProcessingService() {
        return new RequestProcessingServiceImpl(requestProcessors);
    }

    @Bean
    public AccountCreateRequestProcessor accountCreateRequestProcessor() {
        return new AccountCreateRequestProcessor();
    }

    @Bean
    public AccountCountRequestProcessor accountCountRequestProcessor() {
        return new AccountCountRequestProcessor(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        // TODO Should be changed for production quality implementation
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty(DATABASE_DRIVER_CLASS_NAME));
        dataSource.setUrl(environment.getRequiredProperty(DATABASE_URL));
        dataSource.setUsername(environment.getRequiredProperty(DATABASE_USERNAME));
        dataSource.setPassword(environment.getRequiredProperty(DATABASE_PASSWORD));
        return dataSource;
    }
}
