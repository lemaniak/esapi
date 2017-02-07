package com.vicente.mapping.mappers;

import com.vicente.dto.response.RSAccount;
import com.vicente.exception.EsapiException;
import com.vicente.model.Account;
import org.dozer.DozerBeanMapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by vicente on 10/11/16.
 */
@Component
public class RSAccountMapper {

    @Autowired
    private DozerBeanMapper mapper;

    public RSAccount convertFrom(Account account) throws EsapiException{
        try{
            RSAccount rsAccount= mapper.map(account, RSAccount.class);
            return rsAccount;
        }catch (MappingException me){
            throw new EsapiException("esapi.validation.internal.error",me.getCause());
        }

    }

    public Account convertTo(RSAccount rsAccount) throws EsapiException{
        try{
            Account account= mapper.map(rsAccount, Account.class);
            return account;
        }catch(MappingException me){
            throw new EsapiException("esapi.validation.internal.error",me.getCause());
        }
    }
}
