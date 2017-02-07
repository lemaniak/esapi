package com.vicente.dto.request;

import com.vicente.validation.decl.ValidApiKey;


/**
 * Created by vicente on 12/12/16.
 */
public class RQSendEmail extends RQEmail {

    @ValidApiKey
    private String apikey;

    public RQSendEmail() {
    }

    public RQSendEmail(String apikey) {
        this.apikey = apikey;
    }

    public RQSendEmail(String from, String to, Integer port, String mail, String subject, MailType type, String host, String username, String password, String apikey) {
        super(from, to, port, mail, subject, type, host, username, password);
        this.apikey = apikey;
    }

    public RQSendEmail(String from, String to, String mail, String subject, String apikey) {
        super(from, to, mail, subject);
        this.apikey = apikey;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }
}
