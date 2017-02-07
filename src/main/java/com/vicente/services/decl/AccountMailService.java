package com.vicente.services.decl;

import com.vicente.exception.EsapiException;
import com.vicente.exception.EsapiValidationException;
import com.vicente.model.Account;
import com.vicente.model.AccountMail;

/**
 * Created by vicente on 10/11/16.
 */
public interface AccountMailService {

     AccountMail save(AccountMail account) throws EsapiException,EsapiValidationException;
     AccountMail update(AccountMail account) throws EsapiException;
     Boolean isEmailRegistered(String email,Account account) throws EsapiException;
     AccountMail findByEmail(String email, Account account) throws EsapiException;
}
