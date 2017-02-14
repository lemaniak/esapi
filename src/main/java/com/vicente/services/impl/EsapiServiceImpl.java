package com.vicente.services.impl;

import com.vicente.dto.request.MailType;
import com.vicente.dto.request.RQEmail;
import com.vicente.dto.request.RQSendEmail;
import com.vicente.exception.EsapiException;
import com.vicente.exception.EsapiValidationException;
import com.vicente.model.Account;
import com.vicente.model.AccountMail;
import com.vicente.services.decl.AccountMailService;
import com.vicente.services.decl.AccountService;
import com.vicente.services.decl.EmailService;
import com.vicente.services.decl.EsapiService;
import com.vicente.utils.CryptoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by vicente on 12/12/16.
 */
@Component
public class EsapiServiceImpl implements EsapiService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMailService accountMailService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CryptoUtils cryptoUtils;

    @Override
    public void sendEmail(RQSendEmail rqSendEmail) throws EsapiException {
        Account account = accountService.findByApiKey(rqSendEmail.getApikey());
        AccountMail accountMail = accountMailService.findByEmail(rqSendEmail.getFrom(),account);

        if(accountMail==null){
            throw new EsapiValidationException("esapi.email.from.invalid");
        }else{
            rqSendEmail.setType(MailType.HTML);
            rqSendEmail.setHost(accountMail.getHost());
            rqSendEmail.setPort(Integer.parseInt(accountMail.getPort()));
            rqSendEmail.setUsername(rqSendEmail.getFrom());
            rqSendEmail.setPassword(cryptoUtils.decrypt(accountMail.getPassword()));

            emailService.sendEmail(rqSendEmail);
        }
    }
}
