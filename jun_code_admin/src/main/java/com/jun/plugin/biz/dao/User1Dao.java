package com.jun.plugin.biz.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import com.jun.plugin.biz.model.User1;

import java.util.List;

/**
* 
*
* Created by wujun on '2021-09-08 11:52:01'.
*/
@Component
public interface User1Dao {

    /**
    * 新增
    */
    public int insert(@Param("user1") User1 user1);

    /**
    * 删除
    */
    public int delete(@Param("id") int id);

    /**
    * 更新
    */
    public int update(@Param("user1") User1 user1);

    /**
    * Load查询
    */
    public User1 load(@Param("id") int id);

    /**
    * 分页查询Data
    */
	public List<User1> pageList(@Param("offset") int offset,
                                                 @Param("pagesize") int pagesize);

    /**
    * 分页查询Count
    */
    public int pageListCount(@Param("offset") int offset,
                             @Param("pagesize") int pagesize);

}
