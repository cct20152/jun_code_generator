package com.jun.plugin.biz.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import com.jun.plugin.biz.model.Company;

import java.util.List;

/**
* 公司资料表
*
* Created by wujun on '2021-09-08 11:52:01'.
*/
@Component
public interface CompanyDao {

    /**
    * 新增
    */
    public int insert(@Param("company") Company company);

    /**
    * 删除
    */
    public int delete(@Param("id") int id);

    /**
    * 更新
    */
    public int update(@Param("company") Company company);

    /**
    * Load查询
    */
    public Company load(@Param("id") int id);

    /**
    * 分页查询Data
    */
	public List<Company> pageList(@Param("offset") int offset,
                                                 @Param("pagesize") int pagesize);

    /**
    * 分页查询Count
    */
    public int pageListCount(@Param("offset") int offset,
                             @Param("pagesize") int pagesize);

}
