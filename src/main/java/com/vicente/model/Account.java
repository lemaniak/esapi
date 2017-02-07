package com.vicente.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by vicente on 07/11/16.
 */
@Entity
@Table(name = "account", schema = "esapi")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Basic(optional = false)
    private Long id;

    @Column(name = "email")
    @Basic(optional = false)
    private String email;

    @Column(name = "password")
    @Basic(optional = false)
    private String password;

    @Column(name="registration_date")
    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false)
    private Date registration_date;

    @Column(name="api_key")
    @Basic(optional = false)
    private String api_key;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    private Status status;

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        if (Account.class.isInstance(obj)) {
            Account account= Account.class.cast(obj);
            EqualsBuilder eb = new EqualsBuilder();
            eb.append(this.id, account.getId());
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
