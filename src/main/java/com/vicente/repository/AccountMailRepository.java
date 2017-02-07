package com.vicente.repository;

import com.vicente.model.AccountMail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by vicente on 07/11/16.
 */
public interface AccountMailRepository extends JpaRepository<AccountMail,Integer> {
    @Query(name = "AccountMail.isEmailAlreadyRegistered")
    Boolean isEmailAlreadyRegistered(String email,Long accountid);
    @Query(name = "AccountMail.findByEmail")
    AccountMail findByEmail(String email,Long accountid);
}
