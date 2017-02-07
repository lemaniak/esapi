package com.vicente.mapping.mappers;

import com.vicente.dto.request.RQAccountMail;
import com.vicente.dto.request.RQNewAccount;
import com.vicente.exception.EsapiException;
import com.vicente.model.Account;
import com.vicente.model.AccountMail;
import com.vicente.model.Status;
import com.vicente.utils.CryptoUtils;
import com.vicente.utils.TokenUtils;
import com.vicente.utils.Utils;
import org.dozer.DozerBeanMapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by vicente on 10/11/16.
 */
@Component
public class RQAccountMailMapper {

    @Autowired
    private DozerBeanMapper mapper;

    @Autowired
    private CryptoUtils cryptoUtils;

    @Autowired
    private TokenUtils tokenUtils;


    public RQAccountMail convertFrom(AccountMail accountMail) throws EsapiException{
        try{
            RQAccountMail rqAccountMail= mapper.map(accountMail, RQAccountMail.class);
            return rqAccountMail;
        }catch (MappingException me){
            throw new EsapiException("esapi.validation.internal.error",me.getCause());
        }

    }

    public AccountMail convertTo(RQAccountMail rqAccountMail) throws EsapiException{
        try{
            AccountMail accountMail= mapper.map(rqAccountMail, AccountMail.class);
            accountMail.setPassword(cryptoUtils.encrypt(accountMail.getPassword()));
            accountMail.setAccount(tokenUtils.getAccountFromToken(rqAccountMail.getToken()));
            return accountMail;
        }catch(MappingException me){
            throw new EsapiException("esapi.validation.internal.error",me.getCause());
        }
    }
}
