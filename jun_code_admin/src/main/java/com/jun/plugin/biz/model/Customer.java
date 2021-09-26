package com.jun.plugin.biz.model;

import java.io.Serializable;
import java.util.Date;

/**
*  客户资料
*
*  Created by wujun on '2021-09-08 11:52:01'.
*/
public class Customer implements Serializable {
    private static final long serialVersionUID = 42L;

    /**
    * 
    */
    private Integer customerId;

    /**
    * 
    */
    private String name;

    /**
    * 
    */
    private String myid;

    /**
    * A:正常,I:删除
    */
    private String status;

    /**
    * 
    */
    private Date created;

    /**
    * 
    */
    private Date lastmod;

    /**
    * 创建人
    */
    private Integer creater;

    /**
    * 修改人
    */
    private Integer modifiyer;

    /**
    * 客户类型
    */
    private Integer clasid;

    /**
    * 
    */
    private String tel;

    /**
    * 
    */
    private String fax;

    /**
    * 
    */
    private String url;

    /**
    * 
    */
    private String email;

    /**
    * 区域编码
    */
    private Integer areaId;

    /**
    * 区域名称
    */
    private String areaName;

    /**
    * 省份编码
    */
    private Integer provinceId;

    /**
    * 省份名称
    */
    private String provinceName;

    /**
    * 城市编码
    */
    private Integer cityId;

    /**
    * 城市名称
    */
    private String cityName;

    /**
    * 
    */
    private String address;

    /**
    * 
    */
    private String zip;

    /**
    * 客户等级编码
    */
    private Integer levelId;

    /**
    * 客户等级名称
    */
    private String levelName;

    /**
    * 价格等级
    */
    private Integer priceLevel;

    /**
    * 联系周期
    */
    private Integer contacperiod;

    /**
    * 客户来源编码
    */
    private Integer sourceId;

    /**
    * 客户来源名称
    */
    private String sourceName;

    /**
    * 客户公司性质编码
    */
    private Integer natureId;

    /**
    * 客户公司性质名称
    */
    private String natureName;

    /**
    * 行业编码
    */
    private Integer industryId;

    /**
    * 行业名称
    */
    private String industryName;

    /**
    * 主业务业
    */
    private String mainBusiness;

    /**
    * 公司规模编码
    */
    private Integer sizeId;

    /**
    * 公司规模
    */
    private String sizeName;

    /**
    * 开业年份
    */
    private Date setupDate;

    /**
    * 注册资金
    */
    private Integer capital;

    /**
    * 法人
    */
    private String corporation;

    /**
    * 信用编码
    */
    private Integer crediid;

    /**
    * 信用名称
    */
    private String crediname;

    /**
    * 
    */
    private String bank;

    /**
    * 
    */
    private String account;

    /**
    * 税号
    */
    private String taxno;

    /**
    * 共享人(以逗号分隔)
    */
    private String shared;

    /**
    * 客户父项(不用)
    */
    private Integer pid;

    /**
    * 附件编码
    */
    private Integer attachmenid;

    /**
    * 描述
    */
    private String description;

    /**
    * 销售编码
    */
    private Integer saleId;

    /**
    * 销售名称
    */
    private String saleName;

    /**
    * 销售组织编码
    */
    private Integer saleOrganizationId;

    /**
    * 销售组织名称
    */
    private String saleOrganizationName;

    /**
    * 客户状态 T:交易中，S:禁用
    */
    private String customerStatus;

    /**
    * 客户类型名称
    */
    private String clasname;

    /**
    * 
    */
    private Integer empCount;

    /**
    * 税率
    */
    private Integer tax;

    /**
    * 立帐方式 1:出库立账,2:开票立帐,3:不立账(不用)
    */
    private Integer setupAccount;

    /**
    * 币别编码
    */
    private Integer currencyId;

    /**
    * 
    */
    private String currencyName;

    /**
    * 
    */
    private String totalSales;

    /**
    * 1:不含税，2:含税
    */
    private String deductionTax;


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMyid() {
        return myid;
    }

    public void setMyid(String myid) {
        this.myid = myid;
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

    public Integer getCreater() {
        return creater;
    }

    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    public Integer getModifiyer() {
        return modifiyer;
    }

    public void setModifiyer(Integer modifiyer) {
        this.modifiyer = modifiyer;
    }

    public Integer getClasid() {
        return clasid;
    }

    public void setClasid(Integer clasid) {
        this.clasid = clasid;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(Integer priceLevel) {
        this.priceLevel = priceLevel;
    }

    public Integer getContacperiod() {
        return contacperiod;
    }

    public void setContacperiod(Integer contacperiod) {
        this.contacperiod = contacperiod;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public Integer getNatureId() {
        return natureId;
    }

    public void setNatureId(Integer natureId) {
        this.natureId = natureId;
    }

    public String getNatureName() {
        return natureName;
    }

    public void setNatureName(String natureName) {
        this.natureName = natureName;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getMainBusiness() {
        return mainBusiness;
    }

    public void setMainBusiness(String mainBusiness) {
        this.mainBusiness = mainBusiness;
    }

    public Integer getSizeId() {
        return sizeId;
    }

    public void setSizeId(Integer sizeId) {
        this.sizeId = sizeId;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public Date getSetupDate() {
        return setupDate;
    }

    public void setSetupDate(Date setupDate) {
        this.setupDate = setupDate;
    }

    public Integer getCapital() {
        return capital;
    }

    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    public String getCorporation() {
        return corporation;
    }

    public void setCorporation(String corporation) {
        this.corporation = corporation;
    }

    public Integer getCrediid() {
        return crediid;
    }

    public void setCrediid(Integer crediid) {
        this.crediid = crediid;
    }

    public String getCrediname() {
        return crediname;
    }

    public void setCrediname(String crediname) {
        this.crediname = crediname;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTaxno() {
        return taxno;
    }

    public void setTaxno(String taxno) {
        this.taxno = taxno;
    }

    public String getShared() {
        return shared;
    }

    public void setShared(String shared) {
        this.shared = shared;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getAttachmenid() {
        return attachmenid;
    }

    public void setAttachmenid(Integer attachmenid) {
        this.attachmenid = attachmenid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public Integer getSaleOrganizationId() {
        return saleOrganizationId;
    }

    public void setSaleOrganizationId(Integer saleOrganizationId) {
        this.saleOrganizationId = saleOrganizationId;
    }

    public String getSaleOrganizationName() {
        return saleOrganizationName;
    }

    public void setSaleOrganizationName(String saleOrganizationName) {
        this.saleOrganizationName = saleOrganizationName;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public String getClasname() {
        return clasname;
    }

    public void setClasname(String clasname) {
        this.clasname = clasname;
    }

    public Integer getEmpCount() {
        return empCount;
    }

    public void setEmpCount(Integer empCount) {
        this.empCount = empCount;
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public Integer getSetupAccount() {
        return setupAccount;
    }

    public void setSetupAccount(Integer setupAccount) {
        this.setupAccount = setupAccount;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(String totalSales) {
        this.totalSales = totalSales;
    }

    public String getDeductionTax() {
        return deductionTax;
    }

    public void setDeductionTax(String deductionTax) {
        this.deductionTax = deductionTax;
    }

}