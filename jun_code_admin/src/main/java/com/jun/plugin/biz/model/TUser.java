package com.jun.plugin.biz.model;

import java.io.Serializable;
import java.util.Date;

/**
*  
*
*  Created by wujun on '2021-09-08 11:52:01'.
*/
public class TUser implements Serializable {
    private static final long serialVersionUID = 42L;

    /**
    * 用户id
    */
    private String id;

    /**
    * 账户名称
    */
    private String username;

    /**
    * 加密盐值
    */
    private String salt;

    /**
    * 用户密码密文
    */
    private String password;

    /**
    * 手机号码
    */
    private String phone;

    /**
    * 部门id
    */
    private String depid;

    /**
    * 真实名称
    */
    private String realName;

    /**
    * 昵称
    */
    private String nickName;

    /**
    * 邮箱(唯一)
    */
    private String email;

    /**
    * 账户状态(1.正常 2.锁定 )
    */
    private Integer status;

    /**
    * 性别(1.男 2.女)
    */
    private Integer sex;

    /**
    * 是否删除(1未删除；0已删除)
    */
    private Integer deleted;

    /**
    * 创建人
    */
    private String createId;

    /**
    * 更新人
    */
    private String updateId;

    /**
    * 创建来源(1.web 2.android 3.ios )
    */
    private Integer createWhere;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 
    */
    private Date updateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepid() {
        return depid;
    }

    public void setDepid(String depid) {
        this.depid = depid;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public Integer getCreateWhere() {
        return createWhere;
    }

    public void setCreateWhere(Integer createWhere) {
        this.createWhere = createWhere;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}