package com.vicente.dto.response;


/**
 * Created by vicente on 13/11/16.
 */
public class RSAuthenticateAccount extends RSAccount{
    private String token;

    public RSAuthenticateAccount() {
    }

    public RSAuthenticateAccount(String token) {
        this.token = token;
    }

    public RSAuthenticateAccount(Long id, String email, String registration_date, String api_key, String status, String token) {
        super(id, email, registration_date, api_key, status);
        this.token = token;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
