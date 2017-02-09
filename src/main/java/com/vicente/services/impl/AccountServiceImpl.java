package com.vicente.services.impl;

import com.vicente.dto.request.MailType;
import com.vicente.dto.request.RQEmail;
import com.vicente.exception.EsapiException;
import com.vicente.exception.EsapiValidationException;
import com.vicente.model.Account;
import com.vicente.model.Status;
import com.vicente.repository.AccountRepository;
import com.vicente.services.decl.AccountService;
import com.vicente.services.decl.EmailListener;
import com.vicente.services.decl.EmailService;
import com.vicente.utils.CryptoUtils;
import com.vicente.utils.EsapiConfig;
import com.vicente.utils.TokenUtils;
import com.vicente.utils.Utils;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;


/**
 * Created by vicente on 09/11/16.
 */
@Component
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImpl implements AccountService {

    private final static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CryptoUtils cryptoUtils;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private EmailService emailService;
    @Autowired
    private EsapiConfig esapiConfig;
    @Autowired
    private Utils utils;

    public Account save(Account account) throws EsapiException {
        try{
            //generate account
            Account res= accountRepository.saveAndFlush(account);

            //generate activation token
            String activation_token= HtmlUtils.htmlEscape(tokenUtils.getActivationToken(res));

            //send activation email
            RQEmail rqEmail = new RQEmail();
            rqEmail.setFrom(esapiConfig.getUsername());
            rqEmail.setTo(account.getEmail());
            rqEmail.setSubject("Subject");
            rqEmail.setType(MailType.HTML);
            rqEmail.setMail(String.format(utils.readFile("public/activation.html"),activation_token));
            rqEmail.setHost(esapiConfig.getHost());
            rqEmail.setPort(esapiConfig.getPort());
            rqEmail.setUsername(esapiConfig.getUsername());
            rqEmail.setPassword(esapiConfig.getPassword());
            emailService.sendEmail(rqEmail);

            return res;
        }catch(Exception ex){
            logger.info(ex.getMessage(),ex);
            throw new EsapiException("esapi.error.saving.account",ex.getCause());
        }

    }


    public boolean resendActivationEmail(long account_id) throws EsapiException {
            //validate account
            Account account= accountRepository.findOne(account_id);
            if(account==null){
                throw new EsapiValidationException("esapi.account.does.not.exists");
            }else{
                try{
                    //generate activation token
                    String activation_token= HtmlUtils.htmlEscape(tokenUtils.getActivationToken(account));
                    //send activation email
                    RQEmail rqEmail = new RQEmail();
                    rqEmail.setFrom(esapiConfig.getUsername());
                    rqEmail.setTo(account.getEmail());
                    rqEmail.setSubject("Subject");
                    rqEmail.setType(MailType.HTML);
                    rqEmail.setMail(String.format(utils.readFile("public/activation.html"),activation_token));
                    rqEmail.setHost(esapiConfig.getHost());
                    rqEmail.setPort(esapiConfig.getPort());
                    rqEmail.setUsername(esapiConfig.getUsername());
                    rqEmail.setPassword(esapiConfig.getPassword());
                    emailService.sendEmail(rqEmail);
                }catch(Exception ex){
                    logger.info(ex.getMessage(),ex);
                    throw new EsapiException("esapi.validation.internal.error",ex.getCause());
                }
                return true;
            }
    }

    @Override
    public Account update(Account account) throws EsapiException {
        try{
            Account dbaccount = accountRepository.findByEmail(account.getEmail());
            dbaccount.setPassword(account.getPassword());
            return accountRepository.saveAndFlush(account);
        }catch(Exception ex){
            logger.info(ex.getMessage(),ex);
            throw new EsapiException("esapi.error.updating.account",ex.getCause());
        }
    }

    @Override
    public Boolean authenticate(Account account, String password) throws EsapiException {
        try{
            if(account.getPassword().equals(cryptoUtils.encrypt(password))){
                return true;
            }else{
                return false;
            }
        }catch (Exception ex){
            logger.info(ex.getMessage(),ex);
            throw new EsapiException("esapi.error.authenticating.account",ex.getCause());
        }

    }


    public Boolean isEmailRegistered(String email) throws EsapiException{
        try{
            return accountRepository.isEmailAlreadyRegistered(email);
        }catch(Exception ex){
            logger.info(ex.getMessage(),ex);
            throw new EsapiException("esapi.error.saving.account",ex.getCause());
        }
    }

    public Account findByEmail(String email) throws EsapiException{
        try{
            return accountRepository.findByEmail(email);
        }catch(Exception ex){
            logger.info(ex.getMessage(),ex);
            throw new EsapiException("esapi.error.finding.account",ex.getCause());
        }
    }

    @Override
    public Account findByApiKey(String apiKey) throws EsapiException {
        try{
            return accountRepository.findByApiKey(apiKey);
        }catch(Exception ex){
            logger.info(ex.getMessage(),ex);
            throw new EsapiException("esapi.error.finding.account",ex.getCause());
        }
    }

    @Override
    public Boolean activate(String token) throws EsapiValidationException,EsapiException {
       if(tokenUtils.isExpiredToken(cryptoUtils.decrypt(token))){
           return false;
       }else{
           Account tokenAccount = tokenUtils.getAccountFromToken(token);
           tokenAccount.setStatus(Status.ACTIVE);
           this.update(tokenAccount);
           return true;
       }
    }

    @Override
    public boolean existsApiKey(String apikey) throws EsapiException {
        try{
            return accountRepository.isApiKeyRegistered(apikey);
        }catch(Exception ex){
            logger.info(ex.getMessage(),ex);
            throw new EsapiException("esapi.error.invalid.apikey",ex.getCause());
        }
    }




}
