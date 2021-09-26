package com.jun.plugin.biz.service;
import java.util.Map;

import com.jun.plugin.biz.model.City;
import com.jun.plugin.codegenerator.admin.model.ReturnT;

/**
* 城市资料
*
* Created by wujun on '2021-09-08 11:52:01'.
*/
public interface CityService {

    /**
    * 新增
    */
    public ReturnT<String> insert(City city);

    /**
    * 删除
    */
    public ReturnT<String> delete(int id);

    /**
    * 更新
    */
    public ReturnT<String> update(City city);

    /**
    * Load查询
    */
    public City load(int id);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}
