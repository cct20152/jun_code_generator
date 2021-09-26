package com.jun.plugin.biz.service;
import java.util.Map;

import com.jun.plugin.biz.model.Customer;
import com.jun.plugin.codegenerator.admin.model.ReturnT;

/**
* 客户资料
*
* Created by wujun on '2021-09-08 11:52:01'.
*/
public interface CustomerService {

    /**
    * 新增
    */
    public ReturnT<String> insert(Customer customer);

    /**
    * 删除
    */
    public ReturnT<String> delete(int id);

    /**
    * 更新
    */
    public ReturnT<String> update(Customer customer);

    /**
    * Load查询
    */
    public Customer load(int id);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}
