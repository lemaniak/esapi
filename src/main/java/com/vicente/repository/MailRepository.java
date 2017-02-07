package com.vicente.repository;

import com.vicente.model.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vicente on 07/11/16.
 */
public interface MailRepository extends JpaRepository<Mail,Integer> {
}
