package com.vicente.dto.response;

/**
 * Created by vicente on 13/11/16.
 */
public class RSAccount {
    private Long id;
    private String email;
    private String registration_date;
    private String api_key;
    private String status;
    public RSAccount() {
    }

    public RSAccount(Long id, String email, String registration_date, String api_key, String status) {
        this.id = id;
        this.email = email;
        this.registration_date = registration_date;
        this.api_key = api_key;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
