package com.jun.plugin.biz.service;
import java.util.Map;

import com.jun.plugin.biz.model.SysUser;
import com.jun.plugin.codegenerator.admin.model.ReturnT;

/**
* 
*
* Created by wujun on '2021-09-08 11:52:01'.
*/
public interface SysUserService {

    /**
    * 新增
    */
    public ReturnT<String> insert(SysUser sysUser);

    /**
    * 删除
    */
    public ReturnT<String> delete(int id);

    /**
    * 更新
    */
    public ReturnT<String> update(SysUser sysUser);

    /**
    * Load查询
    */
    public SysUser load(int id);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}
