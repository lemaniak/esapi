package com.vicente.repository;

import com.vicente.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by vicente on 07/11/16.
 */
public interface AccountRepository extends JpaRepository<Account,Long> {

        @Query(name = "Account.isEmailAlreadyRegistered")
        Boolean isEmailAlreadyRegistered(String email);
        @Query(name = "Account.findbyemail")
        Account findByEmail(String email);
        @Query(name = "Account.findbyapykey")
        Account findByApiKey(String apikey);
        @Query(name = "Account.isapikey.registered")
        Boolean isApiKeyRegistered(String apiKey);
}
