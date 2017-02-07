package com.vicente.controller;

import com.vicente.dto.request.RQAccountMail;
import com.vicente.dto.response.RSAccountMail;
import com.vicente.exception.EsapiException;
import com.vicente.mapping.mappers.RQAccountMailMapper;
import com.vicente.mapping.mappers.RSAccountMailMapper;
import com.vicente.model.AccountMail;
import com.vicente.services.decl.AccountMailService;
import com.vicente.services.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vicente on 27/01/17.
 */
@RestController
@RequestMapping(value = "api/v1/accountmail/")
public class AccountMailController {

    @Autowired
    private AccountMailService accountMailService;
    @Autowired
    private RQAccountMailMapper rqAccountMailMapper;
    @Autowired
    private RSAccountMailMapper rsAccountMailMapper;

    @RequestMapping(value = "new", method = RequestMethod.POST)
    private RSAccountMail create(@Validated @RequestBody RQAccountMail rqaccountMail) throws EsapiException {
        AccountMail accountMail = rqAccountMailMapper.convertTo(rqaccountMail);
        accountMail=accountMailService.save(accountMail);
        return rsAccountMailMapper.convertFrom(accountMail);
    }
}
