package com.vicente.dto.request;

import com.vicente.validation.decl.ValidEmail;
import com.vicente.validation.decl.ValidStatus;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by vicente on 09/11/16.
 */
public class RQNewAccount {


    @NotNull(message = "esapi.account.email.required")
    @Size(max = 100, min = 5, message = "esapi.account.email.invalid.size")
    @ValidEmail(emailExistValidate = true, errorCode = 1005)
    private String email;

    @NotNull(message = "esapi.account.password.required")
    @Size(max = 50, min = 5, message = "esapi.account.password.invalid.size")
    private String password;


    public RQNewAccount() {
    }

    public RQNewAccount(String email, String password) {
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
