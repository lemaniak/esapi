package com.vicente.dto.request;

import com.vicente.validation.decl.ValidToken;

import javax.validation.constraints.Size;

/**
 * Created by vicente on 09/11/16.
 */
public class RQUpdateAccount {


    @ValidToken
    private String token;

    @Size(max = 50, min = 5, message = "esapi.account.password.invalid.size")
    private String password;


    public RQUpdateAccount() {
    }

    public RQUpdateAccount(String token, String password) {
        this.token = token;
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
