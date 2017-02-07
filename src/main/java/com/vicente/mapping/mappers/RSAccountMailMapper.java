package com.vicente.mapping.mappers;

import com.vicente.dto.response.RSAccount;
import com.vicente.dto.response.RSAccountMail;
import com.vicente.exception.EsapiException;
import com.vicente.model.Account;
import com.vicente.model.AccountMail;
import org.dozer.DozerBeanMapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by vicente on 10/11/16.
 */
@Component
public class RSAccountMailMapper {

    @Autowired
    private DozerBeanMapper mapper;

    public RSAccountMail convertFrom(AccountMail accountMail) throws EsapiException{
        try{
            RSAccountMail rsAccountMail= mapper.map(accountMail, RSAccountMail.class);
            return rsAccountMail;
        }catch (MappingException me){
            throw new EsapiException("esapi.validation.internal.error",me.getCause());
        }

    }

    public AccountMail convertTo(RSAccountMail rsAccountMail) throws EsapiException{
        try{
            AccountMail accountMail= mapper.map(rsAccountMail, AccountMail.class);
            return accountMail;
        }catch(MappingException me){
            throw new EsapiException("esapi.validation.internal.error",me.getCause());
        }
    }
}
