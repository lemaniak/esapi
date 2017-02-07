package com.vicente.config;

import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by vicente on 19/09/16.
 */
@Configuration//specifies class is a config class
public class PersistenceConfiguration {

    @Bean//defines this as a spring bean
    @ConfigurationProperties(prefix = "spring.datasource")//tells the datasource builder to use the configuration and properties  located in the application.properties file where the propertie name stars with spring.datasource
    @Primary//primary default datasource
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "datasource.flyway")
    @FlywayDataSource
    public DataSource flyawayDataSource(){
        return DataSourceBuilder.create().build();
    }
}
