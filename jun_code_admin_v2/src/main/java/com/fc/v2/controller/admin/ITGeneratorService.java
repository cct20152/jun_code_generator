package com.fc.v2.controller.admin;

import com.fc.v2.model.custom.TsysTables;
import com.fc.v2.model.custom.autocode.BeanColumn;


import java.util.List;

/**
 * 生成代码Service接口
 *
 * @author
 * @date 2021-08-05
 */
public interface ITGeneratorService {

	/**
	 * 查询具体某表信息
	 *
	 * @param tableName
	 * @return
	 */
	public List<TsysTables> queryList(String tableName);

	/**
	 * 查询表详情
	 *
	 * @param tableName
	 * @return
	 */
	public List<BeanColumn> queryColumns2(String tableName);
}
