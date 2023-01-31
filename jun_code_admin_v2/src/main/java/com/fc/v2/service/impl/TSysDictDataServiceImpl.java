
package com.fc.v2.service.impl;

import java.util.List;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.v2.mapper.auto.TSysDictDataMapper;
import com.fc.v2.model.auto.TSysDictData;
import org.springframework.stereotype.Service;

import com.fc.v2.service.ITSysDictDataService;


/**
 * 字典数据Service业务层处理
 *
 * @author zhaonz
 * @date 2021-08-06
 */
@Service
public class TSysDictDataServiceImpl  extends ServiceImpl<TSysDictDataMapper, TSysDictData> implements ITSysDictDataService
{


    /**
     * 查询字典数据列表
     *
     * @param queryWrapper 字典数据
     * @return 字典数据
     */
    @Override
    public List<TSysDictData> selectTSysDictDataList(Wrapper<TSysDictData> queryWrapper)
    {
        return this.baseMapper.selectList(queryWrapper);
    }




    /**
     * 删除字典数据信息
     *
     * @param queryWrapper
     * @return
     */
    @Override
    public int deleteTSysDictData(Wrapper<TSysDictData> queryWrapper) {
        return this.baseMapper.delete(queryWrapper);
    }


}
