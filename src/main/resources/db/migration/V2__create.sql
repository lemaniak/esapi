CREATE SCHEMA IF NOT EXISTS esapi;


-- -----------------------------------------------------
-- Table 'esapi'.'account'
-- -----------------------------------------------------

DROP TABLE IF EXISTS esapi.account;

CREATE TABLE esapi.account (
  id BIGINT NOT NULL,
  email VARCHAR(100) NOT NULL,
  password VARCHAR(1000) NOT NULL,
  registration_date TIMESTAMP NOT NULL,
  api_key VARCHAR(1000) NOT NULL,
  status VARCHAR(10) NOT NULL,
  PRIMARY KEY (id));


-- -----------------------------------------------------
-- Table 'esapi'.'account_mails'
-- -----------------------------------------------------

DROP TABLE IF EXISTS esapi.account_mail;

CREATE TABLE esapi.account_mail (
  id INT  NOT NULL,
  account_id BIGINT  NOT NULL,
  email_address VARCHAR(100) NOT NULL,
  password VARCHAR(1000) NOT NULL,
  host VARCHAR(45) NOT NULL,
  port VARCHAR(45) NOT NULL,
  username VARCHAR(45) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_account_mails_account
    FOREIGN KEY (account_id)
    REFERENCES esapi.account (id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

CREATE INDEX fk_account_mails_account_idx ON esapi.account_mail (account_id ASC);


-- -----------------------------------------------------
-- Table mail
-- -----------------------------------------------------
DROP TABLE IF EXISTS esapi.mail ;


CREATE TABLE esapi.mail (
  id BIGINT  NOT NULL,
  from_mail INT  NOT NULL,
  mail VARCHAR(5000) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_mail_account_mails1
    FOREIGN KEY (from_mail)
    REFERENCES esapi.account_mail (id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

CREATE INDEX fk_mail_account_mails1_idx ON esapi.mail (from_mail ASC);


-- -----------------------------------------------------
-- Table recipients
-- -----------------------------------------------------

DROP TABLE IF EXISTS esapi.recipient ;

CREATE TABLE esapi.recipient (
  id BIGINT  NOT NULL,
  email_address VARCHAR(100) NOT NULL,
  mail_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_recipients_mail1
    FOREIGN KEY (mail_id)
    REFERENCES esapi.mail (id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

CREATE INDEX fk_recipients_mail1_idx ON esapi.recipient (mail_id ASC);
