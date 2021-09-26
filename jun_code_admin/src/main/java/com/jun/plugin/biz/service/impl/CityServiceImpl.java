package com.jun.plugin.biz.service.impl;

import org.springframework.stereotype.Service;

import com.jun.plugin.biz.dao.CityDao;
import com.jun.plugin.biz.model.City;
import com.jun.plugin.biz.service.CityService;
import com.jun.plugin.codegenerator.admin.model.ReturnT;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 城市资料
*
* Created by wujun on '2021-09-08 11:52:01'.
*/
@Service
public class CityServiceImpl implements CityService {

	@Resource
	private CityDao cityDao;

	/**
    * 新增
    */
	@Override
	public ReturnT<String> insert(City city) {

		// valid
		if (city == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "必要参数缺失");
        }

		cityDao.insert(city);
        return ReturnT.SUCCESS;
	}

	/**
	* 删除
	*/
	@Override
	public ReturnT<String> delete(int id) {
		int ret = cityDao.delete(id);
		return ret>0?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	/**
	* 更新
	*/
	@Override
	public ReturnT<String> update(City city) {
		int ret = cityDao.update(city);
		return ret>0?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	/**
	* Load查询
	*/
	@Override
	public City load(int id) {
		return cityDao.load(id);
	}

	/**
	* 分页查询
	*/
	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<City> pageList = cityDao.pageList(offset, pagesize);
		int totalCount = cityDao.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}
