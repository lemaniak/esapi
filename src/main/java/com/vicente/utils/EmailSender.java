package com.vicente.utils;

import com.vicente.dto.request.RQEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
/**
 * Created by vicente on 12/12/16.
 */
@Component
public class EmailSender {

    @Autowired
    private Utils utils;


    public void sendEmail(RQEmail rqEmail){

        try {
            // sets SMTP server properties
            Properties properties = new Properties();
            properties.put("mail.smtp.host", rqEmail.getHost());
            properties.put("mail.smtp.port", rqEmail.getPort());
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            // creates a new session with an authenticator
            Authenticator auth = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(rqEmail.getUsername(), rqEmail.getPassword());
                }
            };

            Session session = Session.getInstance(properties, auth);

            // creates a new e-mail message
            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(rqEmail.getUsername()));
            InternetAddress[] toAddresses = { new InternetAddress(rqEmail.getTo()) };
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject(rqEmail.getSubject());
            msg.setSentDate(new Date());
            // set plain text message
            msg.setContent(rqEmail.getMail(), "text/html");
            // sends the e-mail
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
