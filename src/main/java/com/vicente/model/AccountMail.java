package com.vicente.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import javax.persistence.*;

/**
 * Created by vicente on 07/11/16.
 */
@Entity
@Table(name = "account_mail", schema = "esapi")
public class AccountMail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Basic(optional = false)
    private int id;

    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @ManyToOne
    private Account account;

    @Column(name = "email_address")
    @Basic(optional = false)
    private String emailAddress;

    @Column(name = "password")
    @Basic(optional = false)
    private String password;

    @Column(name = "host")
    @Basic(optional = false)
    private String host;

    @Column(name = "port")
    @Basic(optional = false)
    private String port;

    @Column(name = "username")
    @Basic(optional = false)
    private String username;

    public AccountMail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(this.id);
        return hcb.toHashCode();
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object obj) {
        boolean equals = false;
        if (AccountMail.class.isInstance(obj)) {
            AccountMail accountMail= AccountMail.class.cast(obj);
            EqualsBuilder eb = new EqualsBuilder();
            eb.append(this.id, accountMail.getId());
            equals = eb.isEquals();
        }
        return equals;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
