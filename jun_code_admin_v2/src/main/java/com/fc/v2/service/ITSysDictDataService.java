
package com.fc.v2.service;

import java.util.List;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.v2.model.auto.TSysDictData;

/**
 * 字典数据Service接口
 *
 * @author zhaonz
 * @date 2021-08-06
 */

public interface ITSysDictDataService extends IService<TSysDictData> {
;

	/**
	 * 查询字典数据列表
	 *
	 * @param queryWrapper 字典数据
	 * @return 字典数据集合
	 */
	public List<TSysDictData> selectTSysDictDataList(Wrapper<TSysDictData> queryWrapper);



	/**
	 * 删除字典数据信息
	 *
	 * @param queryWrapper
	 * @return
	 */
	public int deleteTSysDictData(Wrapper<TSysDictData> queryWrapper);


}
