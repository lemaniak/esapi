package com.vicente.controller;

import com.vicente.dto.request.RQAuthenticateAccount;
import com.vicente.dto.request.RQNewAccount;
import com.vicente.dto.request.RQUpdateAccount;
import com.vicente.dto.response.RSAccount;
import com.vicente.dto.response.RSAuthenticateAccount;
import com.vicente.exception.EsapiException;
import com.vicente.exception.EsapiValidationException;
import com.vicente.mapping.mappers.RSAccountMapper;
import com.vicente.mapping.mappers.RQNewAccountMapper;
import com.vicente.mapping.mappers.RSAuthenticateAccountMapper;
import com.vicente.model.Account;
import com.vicente.services.impl.AccountServiceImpl;
import com.vicente.utils.CryptoUtils;
import com.vicente.utils.TokenUtils;
import com.vicente.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.Date;


/**
 * Created by vicente on 07/11/16.
 */
@RestController
@RequestMapping(value = "api/v1/")
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;
    @Autowired
    private RQNewAccountMapper newAccountMapper;
    @Autowired
    private RSAccountMapper accountMapper;
    @Autowired
    private RSAuthenticateAccountMapper authenticateAccountMapper;
    @Autowired
    private CryptoUtils cryptoUtils;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private Utils utils;


    @RequestMapping(value = "account", method = RequestMethod.POST)
    private RSAccount create(@Validated @RequestBody RQNewAccount account) throws EsapiException {
        Account newAccount=newAccountMapper.convertTo(account);
        newAccount=accountService.save(newAccount);
        RSAccount response=accountMapper.convertFrom(newAccount);
        return response;
    }

    @RequestMapping(value = "account", method = RequestMethod.PUT)
    private RSAccount update(@Validated @RequestBody RQUpdateAccount updateAccount) throws EsapiException {
        Account account = tokenUtils.getAccountFromToken(updateAccount.getToken());
        account.setPassword(cryptoUtils.encrypt(updateAccount.getPassword()));
        account=accountService.update(account);
        return accountMapper.convertFrom(account);
    }

    @RequestMapping(value = "account/login", method = RequestMethod.POST)
    private RSAuthenticateAccount authenticate(@Validated @RequestBody RQAuthenticateAccount authenticateAccount) throws EsapiException,EsapiValidationException {
        Account account = accountService.findByEmail(authenticateAccount.getEmail());
        Boolean authenticated=accountService.authenticate(account, authenticateAccount.getPassword());
        if(!authenticated){
            throw new EsapiValidationException("esapi.account.invalid.credentials");
        }else{
            RSAuthenticateAccount rsAuthenticateAccount=authenticateAccountMapper.convertFrom(account);
            return  rsAuthenticateAccount;
        }
    }

    @RequestMapping(value = "account/activate", method = RequestMethod.GET,produces =MediaType.TEXT_HTML_VALUE)
    private String authenticate(@RequestParam(value = "token", required = true) String token) throws EsapiException,EsapiValidationException {
       boolean activation = accountService.activate(HtmlUtils.htmlUnescape(token));
        if(activation){
            try {
                return utils.readFile("public/welcome.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                return utils.readFile("public/expired.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "";
    }


    @RequestMapping(value = "account/activate/resend", method = RequestMethod.GET)
    private ResponseEntity resendActivationEmail(@RequestParam(value = "account_id", required = true) long id) throws EsapiException,EsapiValidationException {
        accountService.resendActivationEmail(id);
        return new ResponseEntity(HttpStatus.OK);
    }




    @RequestMapping(value = "test", method = RequestMethod.GET,produces =MediaType.TEXT_HTML_VALUE)
    public String home(){
        try {
            return utils.readFile("public/welcome.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
       return "";
    }
}
