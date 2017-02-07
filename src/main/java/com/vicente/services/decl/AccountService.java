package com.vicente.services.decl;

import com.vicente.exception.EsapiException;
import com.vicente.model.Account;

/**
 * Created by vicente on 10/11/16.
 */
public interface AccountService {

     Account save(Account account) throws EsapiException;
     Account update(Account account) throws EsapiException;
     Boolean authenticate(Account account,String password) throws EsapiException;
     Boolean isEmailRegistered(String email) throws EsapiException;
     Account findByEmail(String email) throws EsapiException;
     Account findByApiKey(String apiKey) throws EsapiException;
     Boolean activate(String token) throws EsapiException;
     boolean existsApiKey(String apikey) throws EsapiException;
}
