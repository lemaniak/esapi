package com.vicente.mapping.mappers;

import com.vicente.dto.request.RQNewAccount;
import com.vicente.exception.EsapiException;
import com.vicente.model.Account;
import com.vicente.model.Status;
import com.vicente.utils.CryptoUtils;
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
public class RQNewAccountMapper {

    @Autowired
    private DozerBeanMapper mapper;
    @Autowired
    private CryptoUtils cryptoUtils;
    @Autowired
    private Utils utils;

    public RQNewAccount convertFrom(Account account) throws EsapiException{
        try{
            RQNewAccount rsAccount= mapper.map(account, RQNewAccount.class);
            return rsAccount;
        }catch (MappingException me){
            throw new EsapiException("esapi.validation.internal.error",me.getCause());
        }

    }

    public Account convertTo(RQNewAccount rqNewAccount) throws EsapiException{
        try{
            Account account= mapper.map(rqNewAccount, Account.class);
            account.setApi_key(utils.generateUniqueId());
            account.setRegistration_date(new Date());
            account.setPassword(cryptoUtils.encrypt(account.getPassword()));
            account.setStatus(Status.INACTIVE);
            return account;
        }catch(MappingException me){
            throw new EsapiException("esapi.validation.internal.error",me.getCause());
        }
    }
}
