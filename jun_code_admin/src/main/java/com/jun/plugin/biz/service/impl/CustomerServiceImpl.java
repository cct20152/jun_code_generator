package com.jun.plugin.biz.service.impl;

import org.springframework.stereotype.Service;

import com.jun.plugin.biz.dao.CustomerDao;
import com.jun.plugin.biz.model.Customer;
import com.jun.plugin.biz.service.CustomerService;
import com.jun.plugin.codegenerator.admin.model.ReturnT;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 客户资料
*
* Created by wujun on '2021-09-08 11:52:01'.
*/
@Service
public class CustomerServiceImpl implements CustomerService {

	@Resource
	private CustomerDao customerDao;

	/**
    * 新增
    */
	@Override
	public ReturnT<String> insert(Customer customer) {

		// valid
		if (customer == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "必要参数缺失");
        }

		customerDao.insert(customer);
        return ReturnT.SUCCESS;
	}

	/**
	* 删除
	*/
	@Override
	public ReturnT<String> delete(int id) {
		int ret = customerDao.delete(id);
		return ret>0?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	/**
	* 更新
	*/
	@Override
	public ReturnT<String> update(Customer customer) {
		int ret = customerDao.update(customer);
		return ret>0?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	/**
	* Load查询
	*/
	@Override
	public Customer load(int id) {
		return customerDao.load(id);
	}

	/**
	* 分页查询
	*/
	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<Customer> pageList = customerDao.pageList(offset, pagesize);
		int totalCount = customerDao.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}
