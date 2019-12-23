package com.lyx.undergraduatejob.search.entity;

import java.io.Serializable;

/**
 * @createTime 2019.12.21.9:48
 */
public class LoginEntity implements Serializable {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
