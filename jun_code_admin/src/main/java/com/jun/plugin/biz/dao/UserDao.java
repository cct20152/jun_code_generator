package com.jun.plugin.biz.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import com.jun.plugin.biz.model.User;

import java.util.List;

/**
* 
*
* Created by wujun on '2021-09-08 11:52:01'.
*/
@Component
public interface UserDao {

    /**
    * 新增
    */
    public int insert(@Param("user") User user);

    /**
    * 删除
    */
    public int delete(@Param("id") int id);

    /**
    * 更新
    */
    public int update(@Param("user") User user);

    /**
    * Load查询
    */
    public User load(@Param("id") int id);

    /**
    * 分页查询Data
    */
	public List<User> pageList(@Param("offset") int offset,
                                                 @Param("pagesize") int pagesize);

    /**
    * 分页查询Count
    */
    public int pageListCount(@Param("offset") int offset,
                             @Param("pagesize") int pagesize);

}
