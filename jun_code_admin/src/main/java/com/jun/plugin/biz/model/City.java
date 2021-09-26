package com.jun.plugin.biz.model;

import java.io.Serializable;
import java.util.Date;

/**
*  城市资料
*
*  Created by wujun on '2021-09-08 11:52:01'.
*/
public class City implements Serializable {
    private static final long serialVersionUID = 42L;

    /**
    * 
    */
    private Integer cityId;

    /**
    * 
    */
    private Integer provinceId;

    /**
    * 
    */
    private String name;

    /**
    * 
    */
    private Date created;

    /**
    * 
    */
    private Date lastmod;

    /**
    * 
    */
    private String status;

    /**
    * 创建人
    */
    private Integer creater;

    /**
    * 修改人
    */
    private Integer modifyer;


    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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