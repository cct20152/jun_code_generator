package com.jun.plugin.biz.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import com.jun.plugin.biz.model.SysUser;

import java.util.List;

/**
* 
*
* Created by wujun on '2021-09-08 11:52:01'.
*/
@Component
public interface SysUserDao {

    /**
    * 新增
    */
    public int insert(@Param("sysUser") SysUser sysUser);

    /**
    * 删除
    */
    public int delete(@Param("id") int id);

    /**
    * 更新
    */
    public int update(@Param("sysUser") SysUser sysUser);

    /**
    * Load查询
    */
    public SysUser load(@Param("id") int id);

    /**
    * 分页查询Data
    */
	public List<SysUser> pageList(@Param("offset") int offset,
                                                 @Param("pagesize") int pagesize);

    /**
    * 分页查询Count
    */
    public int pageListCount(@Param("offset") int offset,
                             @Param("pagesize") int pagesize);

}
