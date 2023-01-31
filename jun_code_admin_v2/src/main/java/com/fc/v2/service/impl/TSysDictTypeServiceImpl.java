
package com.fc.v2.service.impl;


import java.util.List;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.v2.mapper.auto.TSysDictTypeMapper;
import com.fc.v2.model.auto.TSysDictType;
import org.springframework.stereotype.Service;

import com.fc.v2.service.ITSysDictTypeService;


/**
 * 字典类型Service业务层处理
 *
 * @author zhaonz
 * @date 2021-08-06
 */
@Service
public class TSysDictTypeServiceImpl  extends ServiceImpl<TSysDictTypeMapper, TSysDictType> implements ITSysDictTypeService
{



    /**
     * 查询字典类型列表
     *
     * @param queryWrapper 字典类型
     * @return 字典类型
     */
    @Override
    public List<TSysDictType> selectTSysDictTypeList(Wrapper<TSysDictType> queryWrapper)
    {
        return this.baseMapper.selectList(queryWrapper);
    }





}
