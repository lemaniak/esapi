package com.vicente.services.impl;

import com.vicente.exception.EsapiException;
import com.vicente.exception.EsapiValidationException;
import com.vicente.model.Account;
import com.vicente.model.AccountMail;
import com.vicente.repository.AccountMailRepository;
import com.vicente.repository.AccountRepository;
import com.vicente.services.decl.AccountMailService;
import com.vicente.utils.CryptoUtils;
import com.vicente.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by vicente on 09/11/16.
 */
@Component
@Transactional(rollbackFor = Exception.class)
public class AccountMailServiceImpl implements AccountMailService {

    private final static Logger logger = LoggerFactory.getLogger(AccountMailServiceImpl.class);

    @Autowired
    private AccountMailRepository accountMailRepository;
    @Autowired
    private CryptoUtils cryptoUtils;
    @Autowired
    private TokenUtils tokenUtils;


    @Override
    public AccountMail save(AccountMail accountMail) throws EsapiException,EsapiValidationException {

            if(accountMailRepository.isEmailAlreadyRegistered(accountMail.getEmailAddress(),accountMail.getAccount().getId())){
               throw new EsapiValidationException("esapi.accountmail.email.alreadyregistered");
            }else{
                try{
                    AccountMail newaccountMail= accountMailRepository.saveAndFlush(accountMail);
                    return newaccountMail;
                }catch(Exception ex){
                    logger.info(ex.getMessage(),ex);
                    throw new EsapiException("esapi.error.saving.account",ex.getCause());
                }
            }
    }

    @Override
    public AccountMail update(AccountMail account) throws EsapiException {
        return null;
    }

    @Override
    public Boolean isEmailRegistered(String email, Account account) throws EsapiException {
        return null;
    }

    @Override
    public AccountMail findByEmail(String email, Account account) throws EsapiException {
        try{
            return accountMailRepository.findByEmail(email,account.getId());
        }catch(Exception ex){
            logger.info(ex.getMessage(),ex);
            throw new EsapiException("esapi.validation.internal.error",ex.getCause());
        }
    }
}
