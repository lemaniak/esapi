package com.vicente.mapping.mappers;
import com.vicente.dto.response.RSAuthenticateAccount;
import com.vicente.exception.EsapiException;
import com.vicente.model.Account;
import com.vicente.utils.TokenUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by vicente on 10/11/16.
 */
@Component
public class RSAuthenticateAccountMapper {

    @Autowired
    private DozerBeanMapper mapper;

    @Autowired
    private TokenUtils tokenUtils;

    public RSAuthenticateAccount convertFrom(Account account) throws EsapiException{
        try{
            RSAuthenticateAccount rsAuthenticateAccount= mapper.map(account, RSAuthenticateAccount.class);
            rsAuthenticateAccount.setToken(tokenUtils.getToken(account));
            return rsAuthenticateAccount;
        }catch (MappingException me){
            throw new EsapiException("esapi.validation.internal.error",me.getCause());
        }

    }

    public Account convertTo(RSAuthenticateAccount rsAccount) throws EsapiException{
        try{
            Account account= mapper.map(rsAccount, Account.class);
            return account;
        }catch(MappingException me){
            throw new EsapiException("esapi.validation.internal.error",me.getCause());
        }
    }
}
