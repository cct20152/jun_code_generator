package com.bjc.lcp.app.controller;
import com.alibaba.fastjson.JSON;
import com.bjc.lcp.app.vo.AppInfoVO;
import com.bjc.lcp.app.dto.AppInfoDTO;
import com.bjc.lcp.app.mapper.AppInfoMapper;
import com.bjc.lcp.app.entity.AppInfoEntity;
import com.bjc.lcp.app.service.AppInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bjc.lcp.system.common.utils.DataResult;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
* @description 应用表
* @author wujun
* @date 2023-04-24
*/
@Slf4j
@RestController
@RequestMapping("/appInfo")
public class AppInfoController {

    @Resource
    private AppInfoService appInfoService;
    
    @Resource
    private AppInfoMapper appInfoMapper;
    
    @ApiOperation(value = "应用表-新增")
    @PostMapping("/add")
    @RequiresPermissions("appInfo:add")
    public DataResult add(@RequestBody AppInfoEntity vo) {
    	AppInfoDTO dto = new AppInfoDTO();
    	BeanUtils.copyProperties(vo, dto);
         if (ObjectUtils.isEmpty(dto.getAppNo())) {
              return DataResult.fail("参数[appNo]不能为空");
         }
         if (ObjectUtils.isEmpty(dto.getAppType())) {
              return DataResult.fail("参数[appType]不能为空");
         }
         if (ObjectUtils.isEmpty(dto.getAppName())) {
              return DataResult.fail("参数[appName]不能为空");
         }
        LambdaQueryWrapper<AppInfoEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(AppInfoEntity::getAppNo, dto.getAppNo());
        AppInfoEntity entity = appInfoService.getOne(queryWrapper);;
        if (entity != null) {
            return DataResult.fail("数据已存在");
        }
        BeanUtils.copyProperties(dto, entity);
        appInfoService.save(entity);
        return DataResult.success();
    }
    
    @ApiOperation(value = "应用表-删除")
    @DeleteMapping("/remove")
    @RequiresPermissions("appInfo:remove")
    public DataResult delete(@RequestBody AppInfoEntity vo) {
    	AppInfoDTO dto = new AppInfoDTO();
    	BeanUtils.copyProperties(vo, dto);
         if (StringUtils.isEmpty(dto.getAppNo())) {
              return DataResult.fail("参数[appNo]不能为空");
         }
        LambdaQueryWrapper<AppInfoEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(AppInfoEntity::getAppNo, dto.getAppNo());
    	appInfoService.remove(queryWrapper);
        return DataResult.success();
    }

    @ApiOperation(value = "应用表-删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("appInfo:delete")
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids) {
    	appInfoService.removeByIds(ids);
        return DataResult.success();
    }


    @ApiOperation(value = "应用表-更新")
    @PutMapping("/update")
    @RequiresPermissions("appInfo:update")
    public DataResult update(@RequestBody AppInfoVO vo) {
    	AppInfoDTO dto = new AppInfoDTO();
    	BeanUtils.copyProperties(vo, dto);
         if (StringUtils.isEmpty(dto.getAppNo())) {
              return DataResult.fail("参数[appNo]不能为空");
         }
        LambdaQueryWrapper<AppInfoEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(AppInfoEntity::getAppNo, dto.getAppNo());
        AppInfoEntity entity = appInfoService.getOne(queryWrapper);;
        if (entity == null) {
            return DataResult.fail("数据不存在");
        }
        BeanUtils.copyProperties(dto, entity);
        appInfoService.updateById(entity);
        return DataResult.success();
    }
    


    @ApiOperation(value = "应用表-查询单条")
    @PutMapping("/getOne")
    @RequiresPermissions("appInfo:getOne")
    public DataResult getOne(@RequestBody AppInfoVO vo) {
    	AppInfoDTO dto = new AppInfoDTO();
    	BeanUtils.copyProperties(vo, dto);
         if (StringUtils.isEmpty(dto.getAppNo())) {
              return DataResult.fail("参数[appNo]不能为空");
         }
        LambdaQueryWrapper<AppInfoEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(AppInfoEntity::getAppNo, dto.getAppNo());
        AppInfoEntity entity = appInfoService.getOne(queryWrapper);;
        if (entity == null) {
            return DataResult.fail("数据不存在");
        }
        return DataResult.success(entity);
    }
    
    


    @ApiOperation(value = "应用表-查询列表分页数据")
    @PostMapping("/listByPage")
    @RequiresPermissions("appInfo:listByPage")
    public DataResult listByPage(@RequestBody AppInfoVO appInfo) {
        Page page = new Page(appInfo.getPage(), appInfo.getLimit());
        AppInfoDTO dto = new AppInfoDTO();
    	BeanUtils.copyProperties(appInfo, dto);
        LambdaQueryWrapper<AppInfoEntity> queryWrapper = Wrappers.lambdaQuery();
        if (!ObjectUtils.isEmpty(appInfo.getAppNo())) {
            queryWrapper.eq(AppInfoEntity::getAppNo, dto.getAppNo());
        }
        if (!ObjectUtils.isEmpty(appInfo.getTenaid())) {
            queryWrapper.eq(AppInfoEntity::getTenaid, dto.getTenaid());
        }
        if (!ObjectUtils.isEmpty(appInfo.getAppType())) {
            queryWrapper.eq(AppInfoEntity::getAppType, dto.getAppType());
        }
        if (!ObjectUtils.isEmpty(appInfo.getAppName())) {
            queryWrapper.eq(AppInfoEntity::getAppName, dto.getAppName());
        }
        if (!ObjectUtils.isEmpty(appInfo.getAppEngNam())) {
            queryWrapper.eq(AppInfoEntity::getAppEngNam, dto.getAppEngNam());
        }
        if (!ObjectUtils.isEmpty(appInfo.getAppDesc())) {
            queryWrapper.eq(AppInfoEntity::getAppDesc, dto.getAppDesc());
        }
        if (!ObjectUtils.isEmpty(appInfo.getDept())) {
            queryWrapper.eq(AppInfoEntity::getDept, dto.getDept());
        }
        if (!ObjectUtils.isEmpty(appInfo.getRemark())) {
            queryWrapper.eq(AppInfoEntity::getRemark, dto.getRemark());
        }
        if (!ObjectUtils.isEmpty(appInfo.getCreateUser())) {
            queryWrapper.eq(AppInfoEntity::getCreateUser, dto.getCreateUser());
        }
        if (!ObjectUtils.isEmpty(appInfo.getCreateTime())) {
            queryWrapper.eq(AppInfoEntity::getCreateTime, dto.getCreateTime());
        }
        if (!ObjectUtils.isEmpty(appInfo.getUpdateUser())) {
            queryWrapper.eq(AppInfoEntity::getUpdateUser, dto.getUpdateUser());
        }
        if (!ObjectUtils.isEmpty(appInfo.getUpdateTime())) {
            queryWrapper.eq(AppInfoEntity::getUpdateTime, dto.getUpdateTime());
        }
        if (!ObjectUtils.isEmpty(appInfo.getSyflg())) {
            queryWrapper.eq(AppInfoEntity::getSyflg, dto.getSyflg());
        }
        if (!ObjectUtils.isEmpty(appInfo.getDeleted())) {
            queryWrapper.eq(AppInfoEntity::getDeleted, dto.getDeleted());
        }
        IPage<AppInfoEntity> iPage = appInfoService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "应用表-查询全部列表数据")
    @PostMapping("/list")
    @RequiresPermissions("appInfo:list")
    public DataResult findListByPage(@RequestBody AppInfoVO appInfo) {
        LambdaQueryWrapper<AppInfoEntity> queryWrapper = Wrappers.lambdaQuery();
        if (!ObjectUtils.isEmpty(appInfo.getAppNo())) {
            queryWrapper.eq(AppInfoEntity::getAppNo, appInfo.getAppNo());
        }
        if (!ObjectUtils.isEmpty(appInfo.getTenaid())) {
            queryWrapper.eq(AppInfoEntity::getTenaid, appInfo.getTenaid());
        }
        if (!ObjectUtils.isEmpty(appInfo.getAppType())) {
            queryWrapper.eq(AppInfoEntity::getAppType, appInfo.getAppType());
        }
        if (!ObjectUtils.isEmpty(appInfo.getAppName())) {
            queryWrapper.eq(AppInfoEntity::getAppName, appInfo.getAppName());
        }
        if (!ObjectUtils.isEmpty(appInfo.getAppEngNam())) {
            queryWrapper.eq(AppInfoEntity::getAppEngNam, appInfo.getAppEngNam());
        }
        if (!ObjectUtils.isEmpty(appInfo.getAppDesc())) {
            queryWrapper.eq(AppInfoEntity::getAppDesc, appInfo.getAppDesc());
        }
        if (!ObjectUtils.isEmpty(appInfo.getDept())) {
            queryWrapper.eq(AppInfoEntity::getDept, appInfo.getDept());
        }
        if (!ObjectUtils.isEmpty(appInfo.getRemark())) {
            queryWrapper.eq(AppInfoEntity::getRemark, appInfo.getRemark());
        }
        if (!ObjectUtils.isEmpty(appInfo.getCreateUser())) {
            queryWrapper.eq(AppInfoEntity::getCreateUser, appInfo.getCreateUser());
        }
        if (!ObjectUtils.isEmpty(appInfo.getCreateTime())) {
            queryWrapper.eq(AppInfoEntity::getCreateTime, appInfo.getCreateTime());
        }
        if (!ObjectUtils.isEmpty(appInfo.getUpdateUser())) {
            queryWrapper.eq(AppInfoEntity::getUpdateUser, appInfo.getUpdateUser());
        }
        if (!ObjectUtils.isEmpty(appInfo.getUpdateTime())) {
            queryWrapper.eq(AppInfoEntity::getUpdateTime, appInfo.getUpdateTime());
        }
        if (!ObjectUtils.isEmpty(appInfo.getSyflg())) {
            queryWrapper.eq(AppInfoEntity::getSyflg, appInfo.getSyflg());
        }
        if (!ObjectUtils.isEmpty(appInfo.getDeleted())) {
            queryWrapper.eq(AppInfoEntity::getDeleted, appInfo.getDeleted());
        }
        List<AppInfoEntity> list = appInfoService.list(queryWrapper);
        return DataResult.success(list);
    }


}

