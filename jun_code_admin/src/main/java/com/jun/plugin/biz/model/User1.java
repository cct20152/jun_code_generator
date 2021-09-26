package com.jun.plugin.biz.model;

import java.io.Serializable;
import java.util.Date;

/**
*  
*
*  Created by wujun on '2021-09-08 11:52:01'.
*/
public class User1 implements Serializable {
    private static final long serialVersionUID = 42L;

    /**
    * 
    */
    private Integer id;

    /**
    * 
    */
    private String username;

    /**
    * 
    */
    private String password;

    /**
    * 
    */
    private String nickName;

    /**
    * 
    */
    private Integer sex;

    /**
    * 
    */
    private Date registerDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

}