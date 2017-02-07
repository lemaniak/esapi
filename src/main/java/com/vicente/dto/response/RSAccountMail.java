package com.vicente.dto.response;


import com.vicente.validation.decl.ValidEmail;
import com.vicente.validation.decl.ValidToken;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by vicente on 12/12/16.
 */
public class RSAccountMail implements Serializable{

    private int id;
    private String emailAddress;
    private Integer port;
    private String host;
    private String username;

    public RSAccountMail() {
    }

    public RSAccountMail(int id, String emailAddress, Integer port, String host, String username) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.port = port;
        this.host = host;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
