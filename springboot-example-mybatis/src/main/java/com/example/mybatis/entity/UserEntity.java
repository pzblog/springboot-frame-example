package com.example.mybatis.entity;

public class UserEntity {

    private Long id;

    private String email;

    private String nickName;

    private String passWord;

    private String regTime;

    private String userName;


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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserEntity() {
    }

    public UserEntity(Long id, String email, String nickName, String passWord, String regTime, String userName) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
        this.passWord = passWord;
        this.regTime = regTime;
        this.userName = userName;
    }
}
