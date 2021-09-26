package com.jun.plugin.biz.service;
import java.util.Map;

import com.jun.plugin.biz.model.User1;
import com.jun.plugin.codegenerator.admin.model.ReturnT;

/**
* 
*
* Created by wujun on '2021-09-08 11:52:01'.
*/
public interface User1Service {

    /**
    * 新增
    */
    public ReturnT<String> insert(User1 user1);

    /**
    * 删除
    */
    public ReturnT<String> delete(int id);

    /**
    * 更新
    */
    public ReturnT<String> update(User1 user1);

    /**
    * Load查询
    */
    public User1 load(int id);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}
