package com.vicente.repository;

import com.vicente.model.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vicente on 07/11/16.
 */
public interface RecipientRepository extends JpaRepository<Recipient,Integer> {
}
