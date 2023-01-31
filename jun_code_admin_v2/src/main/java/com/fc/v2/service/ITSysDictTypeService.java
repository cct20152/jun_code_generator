
package com.fc.v2.service;

import java.util.List;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.v2.model.auto.TSysDictType;

/**
 * 字典类型Service接口
 *
 * @author zhaonz
 * @date 2021-08-06
 */
public interface ITSysDictTypeService extends IService<TSysDictType> {


	/**
	 * 查询字典类型列表
	 *
	 * @param queryWrapper 字典类型
	 * @return 字典类型集合
	 */
	public List<TSysDictType> selectTSysDictTypeList(Wrapper<TSysDictType> queryWrapper);


}
