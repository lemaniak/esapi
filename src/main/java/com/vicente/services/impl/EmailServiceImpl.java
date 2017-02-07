package com.vicente.services.impl;

import com.vicente.dto.request.RQEmail;
import com.vicente.services.decl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by vicente on 12/12/16.
 */
@Component
public class EmailServiceImpl implements EmailService {



    private static final String EMAIL_QUEUE = "email.queue";
    private final JmsTemplate jmsTemplate;

    @Autowired
    public EmailServiceImpl(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }


    @Override
    public void sendEmail(RQEmail rqEmail) {
        jmsTemplate.convertAndSend(EMAIL_QUEUE,rqEmail);
    }
}
