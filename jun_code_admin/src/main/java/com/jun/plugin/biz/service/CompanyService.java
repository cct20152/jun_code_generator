package com.jun.plugin.biz.service;
import java.util.Map;

import com.jun.plugin.biz.model.Company;
import com.jun.plugin.codegenerator.admin.model.ReturnT;

/**
* 公司资料表
*
* Created by wujun on '2021-09-08 11:52:01'.
*/
public interface CompanyService {

    /**
    * 新增
    */
    public ReturnT<String> insert(Company company);

    /**
    * 删除
    */
    public ReturnT<String> delete(int id);

    /**
    * 更新
    */
    public ReturnT<String> update(Company company);

    /**
    * Load查询
    */
    public Company load(int id);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}
