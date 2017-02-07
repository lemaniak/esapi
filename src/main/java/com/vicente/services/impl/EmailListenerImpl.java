package com.vicente.services.impl;

import com.vicente.dto.request.RQEmail;
import com.vicente.services.decl.EmailListener;
import com.vicente.utils.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by vicente on 12/12/16.
 */
@Component
public class EmailListenerImpl  implements EmailListener{

    @Autowired
    private EmailSender emailUtils;

    @Override
    @JmsListener(destination = "email.queue")
    public void processEmail(RQEmail rqEmail) {
       emailUtils.sendEmail(rqEmail);
    }
}
