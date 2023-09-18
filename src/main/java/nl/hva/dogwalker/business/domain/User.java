package nl.hva.dogwalker.business.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:
 * Author: Petya Katsarova
 * Email: pskpetya@gmail.com
 * Created on: 12/09/2023 13:53
 */
public class User {
    private final Logger    logger = LoggerFactory.getLogger(User.class);
    private int              id;
    private String          password;
    private String          email;
    private String          salt;
    private boolean         isVerified;
    private String          jwtToken;

    public User() {
        super();
        this.id = 0;
        this.email = "";
        this.password = "";
        this.salt = null;
        this.isVerified = false;
        this.jwtToken = null;
        logger.info("New User created");
    }

    public User(String email, String password) {
        super();
        this.id = 0;
        this.email = email;
        this.password = password;
        this.salt = null;
        this.jwtToken = null;
        this.isVerified = false;
        logger.info("New User created");
    }

    public User(String email, String password, String salt) {
        super();
        this.id = 0;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.jwtToken = null;
        this.isVerified = false;
        logger.info("New User created");
    }

    public String getPasswordHash() {
        return password;
    }

    public void setPasswordHash(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdU() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}

