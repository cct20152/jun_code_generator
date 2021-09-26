package com.jun.plugin.biz.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import com.jun.plugin.biz.model.City;

import java.util.List;

/**
* 城市资料
*
* Created by wujun on '2021-09-08 11:52:01'.
*/
@Component
public interface CityDao {

    /**
    * 新增
    */
    public int insert(@Param("city") City city);

    /**
    * 删除
    */
    public int delete(@Param("id") int id);

    /**
    * 更新
    */
    public int update(@Param("city") City city);

    /**
    * Load查询
    */
    public City load(@Param("id") int id);

    /**
    * 分页查询Data
    */
	public List<City> pageList(@Param("offset") int offset,
                                                 @Param("pagesize") int pagesize);

    /**
    * 分页查询Count
    */
    public int pageListCount(@Param("offset") int offset,
                             @Param("pagesize") int pagesize);

}
