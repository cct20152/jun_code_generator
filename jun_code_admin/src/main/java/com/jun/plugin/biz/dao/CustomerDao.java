package com.jun.plugin.biz.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import com.jun.plugin.biz.model.Customer;

import java.util.List;

/**
* 客户资料
*
* Created by wujun on '2021-09-08 11:52:01'.
*/
@Component
public interface CustomerDao {

    /**
    * 新增
    */
    public int insert(@Param("customer") Customer customer);

    /**
    * 删除
    */
    public int delete(@Param("id") int id);

    /**
    * 更新
    */
    public int update(@Param("customer") Customer customer);

    /**
    * Load查询
    */
    public Customer load(@Param("id") int id);

    /**
    * 分页查询Data
    */
	public List<Customer> pageList(@Param("offset") int offset,
                                                 @Param("pagesize") int pagesize);

    /**
    * 分页查询Count
    */
    public int pageListCount(@Param("offset") int offset,
                             @Param("pagesize") int pagesize);

}
