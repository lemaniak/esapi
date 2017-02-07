package com.vicente.dto.request;

/**
 * Created by vicente on 12/12/16.
 */
public enum MailType {
    HTML("text/html");

    private String type;

    MailType(String type) {
        this.type = type;
    }

    public String getValue() {
        return type;
    }

}
