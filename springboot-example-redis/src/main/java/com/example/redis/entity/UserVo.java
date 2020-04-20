package com.example.redis.entity;

import java.io.Serializable;

public class UserVo implements Serializable {

    private static final long serialVersionUID = 2900241477529094108L;
    private String userId;

    private String userName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserVo() {
    }

    public UserVo(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
