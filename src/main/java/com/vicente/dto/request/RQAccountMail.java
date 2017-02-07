package com.vicente.dto.request;


import com.vicente.validation.decl.NotBlank;
import com.vicente.validation.decl.ValidEmail;
import com.vicente.validation.decl.ValidToken;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * Created by vicente on 12/12/16.
 */
public class RQAccountMail implements Serializable{


    @ValidToken
    private String token;
    @NotNull(message = "esapi.accountmail.email.required")
    @NotBlank(message = "esapi.accountmail.email.required")
    @ValidEmail(emailExistValidate = false, errorCode = 1005)
    private String emailAddress;
    @NotNull(message = "esapi.accountmail.port.required")
    @Max(value = 9999, message = "esapi.accountmail.port.max")
    @Min(value = 0, message = "esapi.accountmail.port.min")
    private Integer port;
    @NotNull(message = "esapi.accountmail.host.required")
    @NotBlank(message = "esapi.accountmail.host.required")
    private String host;
    @NotNull(message = "esapi.accountmail.username.required")
    @NotBlank(message = "esapi.accountmail.username.required")
    private String username;
    @NotNull(message = "esapi.accountmail.password.required")
    @NotBlank(message = "esapi.accountmail.password.required")
    private String password;

    public RQAccountMail() {
    }

    public RQAccountMail(String token, String emailAddress, Integer port, String host, String username, String password) {
        this.token = token;
        this.emailAddress = emailAddress;
        this.port = port;
        this.host = host;
        this.username = username;
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
