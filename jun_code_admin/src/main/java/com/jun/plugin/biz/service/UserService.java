package com.jun.plugin.biz.service;
import java.util.Map;

import com.jun.plugin.biz.model.User;
import com.jun.plugin.codegenerator.admin.model.ReturnT;

/**
* 
*
* Created by wujun on '2021-09-08 11:52:01'.
*/
public interface UserService {

    /**
    * 新增
    */
    public ReturnT<String> insert(User user);

    /**
    * 删除
    */
    public ReturnT<String> delete(int id);

    /**
    * 更新
    */
    public ReturnT<String> update(User user);

    /**
    * Load查询
    */
    public User load(int id);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}
