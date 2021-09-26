package com.jun.plugin.biz.model;

import java.io.Serializable;
import java.util.Date;

/**
*  公司资料表
*
*  Created by wujun on '2021-09-08 11:52:01'.
*/
public class Company implements Serializable {
    private static final long serialVersionUID = 42L;

    /**
    * 公司自动ID
    */
    private Integer companyId;

    /**
    * 公司名称
    */
    private String name;

    /**
    * 公司电话
    */
    private String tel;

    /**
    * 公司传真
    */
    private String fax;

    /**
    * 公司地址
    */
    private String address;

    /**
    * 邮政编码
    */
    private String zip;

    /**
    * 公司邮件地址
    */
    private String email;

    /**
    * 公司联络人
    */
    private String contact;

    /**
    * 状态
    */
    private String status;

    /**
    * 创造日期
    */
    private Date created;

    /**
    * 修改日期
    */
    private Date lastmod;

    /**
    * 公司负责人
    */
    private String manager;

    /**
    * 开户行
    */
    private String bank;

    /**
    * 银行账号
    */
    private String bankaccount;

    /**
    * 备注
    */
    private String description;

    /**
    * 创建人
    */
    private Integer creater;

    /**
    * 修改人
    */
    private Integer modifyer;


    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastmod() {
        return lastmod;
    }

    public void setLastmod(Date lastmod) {
        this.lastmod = lastmod;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankaccount() {
        return bankaccount;
    }

    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCreater() {
        return creater;
    }

    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    public Integer getModifyer() {
        return modifyer;
    }

    public void setModifyer(Integer modifyer) {
        this.modifyer = modifyer;
    }

}