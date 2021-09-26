package com.jun.plugin.biz.service.impl;

import org.springframework.stereotype.Service;

import com.jun.plugin.biz.dao.TUserDao;
import com.jun.plugin.biz.model.TUser;
import com.jun.plugin.biz.service.TUserService;
import com.jun.plugin.codegenerator.admin.model.ReturnT;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 
*
* Created by wujun on '2021-09-08 11:52:01'.
*/
@Service
public class TUserServiceImpl implements TUserService {

	@Resource
	private TUserDao tUserDao;

	/**
    * 新增
    */
	@Override
	public ReturnT<String> insert(TUser tUser) {

		// valid
		if (tUser == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "必要参数缺失");
        }

		tUserDao.insert(tUser);
        return ReturnT.SUCCESS;
	}

	/**
	* 删除
	*/
	@Override
	public ReturnT<String> delete(int id) {
		int ret = tUserDao.delete(id);
		return ret>0?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	/**
	* 更新
	*/
	@Override
	public ReturnT<String> update(TUser tUser) {
		int ret = tUserDao.update(tUser);
		return ret>0?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	/**
	* Load查询
	*/
	@Override
	public TUser load(int id) {
		return tUserDao.load(id);
	}

	/**
	* 分页查询
	*/
	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<TUser> pageList = tUserDao.pageList(offset, pagesize);
		int totalCount = tUserDao.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}
