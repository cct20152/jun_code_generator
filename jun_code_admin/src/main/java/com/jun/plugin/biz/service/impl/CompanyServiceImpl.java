package com.jun.plugin.biz.service.impl;

import org.springframework.stereotype.Service;

import com.jun.plugin.biz.dao.CompanyDao;
import com.jun.plugin.biz.model.Company;
import com.jun.plugin.biz.service.CompanyService;
import com.jun.plugin.codegenerator.admin.model.ReturnT;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 公司资料表
*
* Created by wujun on '2021-09-08 11:52:01'.
*/
@Service
public class CompanyServiceImpl implements CompanyService {

	@Resource
	private CompanyDao companyDao;

	/**
    * 新增
    */
	@Override
	public ReturnT<String> insert(Company company) {

		// valid
		if (company == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "必要参数缺失");
        }

		companyDao.insert(company);
        return ReturnT.SUCCESS;
	}

	/**
	* 删除
	*/
	@Override
	public ReturnT<String> delete(int id) {
		int ret = companyDao.delete(id);
		return ret>0?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	/**
	* 更新
	*/
	@Override
	public ReturnT<String> update(Company company) {
		int ret = companyDao.update(company);
		return ret>0?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	/**
	* Load查询
	*/
	@Override
	public Company load(int id) {
		return companyDao.load(id);
	}

	/**
	* 分页查询
	*/
	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<Company> pageList = companyDao.pageList(offset, pagesize);
		int totalCount = companyDao.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}
