package com.jun.plugin.biz.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import com.jun.plugin.biz.model.TUser;

import java.util.List;

/**
* 
*
* Created by wujun on '2021-09-08 11:52:01'.
*/
@Component
public interface TUserDao {

    /**
    * 新增
    */
    public int insert(@Param("tUser") TUser tUser);

    /**
    * 删除
    */
    public int delete(@Param("id") int id);

    /**
    * 更新
    */
    public int update(@Param("tUser") TUser tUser);

    /**
    * Load查询
    */
    public TUser load(@Param("id") int id);

    /**
    * 分页查询Data
    */
	public List<TUser> pageList(@Param("offset") int offset,
                                                 @Param("pagesize") int pagesize);

    /**
    * 分页查询Count
    */
    public int pageListCount(@Param("offset") int offset,
                             @Param("pagesize") int pagesize);

}
