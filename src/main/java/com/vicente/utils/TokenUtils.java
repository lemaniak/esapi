package com.vicente.utils;

import com.vicente.exception.EsapiException;
import com.vicente.exception.EsapiValidationException;
import com.vicente.model.Account;
import com.vicente.services.decl.AccountService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by vicente on 17/11/16.
 */
@Component
public class TokenUtils {

    private static final int EXPIRATION_HOURS=12;
    private static final int ACTIVATE_EMAIL_EXPIRATION_HOURS=24;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CryptoUtils cryptoUtils;

    @Autowired
    private Utils utils;


    public String getToken(Account account) throws EsapiException {
        Date expiration_date= new Date();
        DateUtils.addHours(expiration_date, ACTIVATE_EMAIL_EXPIRATION_HOURS);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id:" + account.getId() + "|" + "email:" + account.getEmail() + "|" + "expirationDate:" + utils.dateToString(expiration_date));
        return cryptoUtils.encrypt(stringBuilder.toString());
    }

    public String getActivationToken(Account account) throws EsapiException {
        Date expiration_date= new Date();
        DateUtils.addHours(expiration_date, EXPIRATION_HOURS);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id:" + account.getId() + "|" + "email:" + account.getEmail() + "|" + "expirationDate:" + utils.dateToString(expiration_date));
        return cryptoUtils.encrypt(stringBuilder.toString());
    }

    public boolean isExpiredToken(String decryptedToken) throws EsapiValidationException,EsapiException {
        Date date = null;
        String delims = "|";
        if (!decryptedToken.contains(delims)) {
            throw new EsapiValidationException("esapi.error.invalid.token");
        }
        String[] tokens = decryptedToken.split("\\" + delims);
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].contains("expirationDate")) {
                int index = tokens[i].indexOf(":");
                String expiration_date = tokens[i].substring(index + 1);
                date = utils.stringToDate(expiration_date);
            }
        }

        if (date.after(new Date())) {
            return true;
        } else {
            return false;
        }

    }


    public Account getAccountFromToken(String token) throws EsapiException {
        if(StringUtils.isBlank(token)){
            throw new EsapiException("esapi.validation.internal.error",new IllegalArgumentException("empty token"));
        }else{
            String email=null;
            String decryptedToken=cryptoUtils.decrypt(token);
            String delims = "|";
            if (!decryptedToken.contains(delims)) {
                throw new EsapiValidationException("esapi.error.invalid.token");
            }
            String[] tokens = decryptedToken.split("\\" + delims);
            for (int i = 0; i < tokens.length; i++) {
                if (tokens[i].contains("email")) {
                    int index = tokens[i].indexOf(":");
                    email = tokens[i].substring(index + 1);
                    break;
                }
            }
            return accountService.findByEmail(email);
        }
    }
}
