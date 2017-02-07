package com.vicente.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import javax.persistence.*;

/**
 * Created by vicente on 07/11/16.
 */
@Entity
@Table(name = "mail", schema = "esapi")
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "mail")
    @Basic(optional = false)
    private String mail;

    @JoinColumn(name="from_mail", referencedColumnName = "id")
    @ManyToOne
    private AccountMail fromMail;


    public Mail() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public AccountMail getFromMail() {
        return fromMail;
    }

    public void setFromMail(AccountMail fromMail) {
        this.fromMail = fromMail;
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
        if (Mail.class.isInstance(obj)) {
            Mail mail= Mail.class.cast(obj);
            EqualsBuilder eb = new EqualsBuilder();
            eb.append(this.id, mail.getId());
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
