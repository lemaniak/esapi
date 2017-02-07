package com.vicente.dto.request;


import com.vicente.validation.decl.NotBlank;
import com.vicente.validation.decl.ValidEmail;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by vicente on 12/12/16.
 */
public class RQEmail implements Serializable{

    @NotBlank(message = "esapi.emailsender.required")
    private String from;
    @NotNull(message = "esapi.emailrecipient.required")
    @ValidEmail(emailExistValidate = false)
    private String to;
    private Integer port;
    @NotBlank(message = "esapi.emailbody.required")
    private String mail;
    @NotBlank(message = "esapi.emailsubject.required")
    private String subject;
    private MailType type;
    private String host;
    private String username;
    private String password;

    public RQEmail() {
    }

    public RQEmail(String from, String to, Integer port, String mail, String subject, MailType type, String host, String username, String password) {
        this.from = from;
        this.to = to;
        this.port = port;
        this.mail = mail;
        this.subject = subject;
        this.type = type;
        this.host = host;
        this.username = username;
        this.password = password;
    }

    public RQEmail(String from, String to, String mail, String subject) {
        this.from = from;
        this.to = to;
        this.mail = mail;
        this.subject = subject;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public MailType getType() {
        return type;
    }

    public void setType(MailType type) {
        this.type = type;
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
