package com.jun.plugin.biz.service;
import java.util.Map;

import com.jun.plugin.biz.model.TUser;
import com.jun.plugin.codegenerator.admin.model.ReturnT;

/**
* 
*
* Created by wujun on '2021-09-08 11:52:01'.
*/
public interface TUserService {

    /**
    * 新增
    */
    public ReturnT<String> insert(TUser tUser);

    /**
    * 删除
    */
    public ReturnT<String> delete(int id);

    /**
    * 更新
    */
    public ReturnT<String> update(TUser tUser);

    /**
    * Load查询
    */
    public TUser load(int id);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}
