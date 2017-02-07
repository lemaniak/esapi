package com.vicente.dto.request;

import com.vicente.validation.decl.EmailExists;

import javax.validation.constraints.Size;

/**
 * Created by vicente on 09/11/16.
 */
public class RQAuthenticateAccount {

    @EmailExists
    private String email;

    @Size(max = 50, min = 5, message = "esapi.account.password.invalid.size")
    private String password;


    public RQAuthenticateAccount() {
    }

    public RQAuthenticateAccount(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
