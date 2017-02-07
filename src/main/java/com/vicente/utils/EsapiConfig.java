package com.vicente.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by vicente on 12/12/16.
 */
@Component
@PropertySource("application.properties")
public class EsapiConfig {

    @Value("${esapi.email.host}")
    private String host;
    @Value("${esapi.email.port}")
    private Integer port;
    @Value("${esapi.email.username}")
    private String username;
    @Value("${esapi.email.password}")
    private String password;
    @Value("${esapi.activemq.broker.url}")
    private String brokerURL;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBrokerURL() {
        return brokerURL;
    }

    public void setBrokerURL(String brokerURL) {
        this.brokerURL = brokerURL;
    }
}
