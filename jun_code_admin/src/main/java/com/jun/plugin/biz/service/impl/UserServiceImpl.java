package com.jun.plugin.biz.service.impl;

import org.springframework.stereotype.Service;

import com.jun.plugin.biz.dao.UserDao;
import com.jun.plugin.biz.model.User;
import com.jun.plugin.biz.service.UserService;
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
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	/**
    * 新增
    */
	@Override
	public ReturnT<String> insert(User user) {

		// valid
		if (user == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "必要参数缺失");
        }

		userDao.insert(user);
        return ReturnT.SUCCESS;
	}

	/**
	* 删除
	*/
	@Override
	public ReturnT<String> delete(int id) {
		int ret = userDao.delete(id);
		return ret>0?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	/**
	* 更新
	*/
	@Override
	public ReturnT<String> update(User user) {
		int ret = userDao.update(user);
		return ret>0?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	/**
	* Load查询
	*/
	@Override
	public User load(int id) {
		return userDao.load(id);
	}

	/**
	* 分页查询
	*/
	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<User> pageList = userDao.pageList(offset, pagesize);
		int totalCount = userDao.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}
