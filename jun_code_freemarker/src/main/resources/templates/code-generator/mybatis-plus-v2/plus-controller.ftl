<#if isWithPackage?exists && isWithPackage==true>package ${packageName}.controller;</#if>
<#if isAutoImport?exists && isAutoImport==true>
import com.alibaba.fastjson.JSON;
import ${packageName}.vo.${classInfo.className}VO;
import ${packageName}.dto.${classInfo.className}DTO;
import ${packageName}.mapper.${classInfo.className}Mapper;
import ${packageName}.entity.${classInfo.className}Entity;
import ${packageName}.service.${classInfo.className}Service;
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
</#if>
/**
* @description ${classInfo.classComment}
* @author ${authorName}
* @date ${.now?string('yyyy-MM-dd')}
*/
@Slf4j
@RestController
@RequestMapping("/${classInfo.className?uncap_first}")
public class ${classInfo.className}Controller {

    @Resource
    private ${classInfo.className}Service ${classInfo.className?uncap_first}Service;
    
    @Resource
    private ${classInfo.className}Mapper ${classInfo.className?uncap_first}Mapper;
    
    @ApiOperation(value = "新增")
    @PostMapping("/add")
    @RequiresPermissions("${classInfo.className?uncap_first}:add")
    public DataResult add(@RequestBody ${classInfo.className}Entity vo) {
    	${classInfo.className}DTO dto = new ${classInfo.className}DTO();
    	BeanUtils.copyProperties(vo, dto);
<#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
<#list classInfo.fieldList as fieldItem >
<#if fieldItem.nullable==true>
         if (ObjectUtils.isEmpty(dto.get${fieldItem.fieldName?cap_first}())) {
              return DataResult.fail("参数[${fieldItem.fieldName}]不能为空");
         }
</#if>
</#list>
</#if>
        LambdaQueryWrapper<${classInfo.className}Entity> queryWrapper = Wrappers.lambdaQuery();
<#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
<#list classInfo.fieldList as fieldItem >
<#if fieldItem.isPrimaryKey==true>
        queryWrapper.eq(${classInfo.className}Entity::get${fieldItem.fieldName?cap_first}, dto.get${fieldItem.fieldName?cap_first}());
</#if>
</#list>
</#if>
        ${classInfo.className}Entity entity = ${classInfo.className?uncap_first}Service.getOne(queryWrapper);;
        if (entity != null) {
            return DataResult.fail("数据已存在");
        }
        BeanUtils.copyProperties(dto, entity);
        ${classInfo.className?uncap_first}Service.save(entity);
        return DataResult.success();
    }
    
    @ApiOperation(value = "删除")
    @DeleteMapping("/remove")
    @RequiresPermissions("${classInfo.className?uncap_first}:remove")
    public DataResult delete(@RequestBody ${classInfo.className}Entity vo) {
    	${classInfo.className}DTO dto = new ${classInfo.className}DTO();
    	BeanUtils.copyProperties(vo, dto);
<#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
<#list classInfo.fieldList as fieldItem >
<#if fieldItem.isPrimaryKey==true>
         if (StringUtils.isEmpty(dto.get${fieldItem.fieldName?cap_first}())) {
              return DataResult.fail("参数[${fieldItem.fieldName}]不能为空");
         }
</#if>
</#list>
</#if>
        LambdaQueryWrapper<${classInfo.className}Entity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(${classInfo.className}Entity::getAppNo, dto.getAppNo());
    	${classInfo.className?uncap_first}Service.remove(queryWrapper);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("${classInfo.className?uncap_first}:delete")
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids) {
    	${classInfo.className?uncap_first}Service.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("/update")
    @RequiresPermissions("${classInfo.className?uncap_first}:update")
    public DataResult update(@RequestBody ${classInfo.className}VO vo) {
    	${classInfo.className}DTO dto = new ${classInfo.className}DTO();
    	BeanUtils.copyProperties(vo, dto);
<#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
<#list classInfo.fieldList as fieldItem >
<#if fieldItem.isPrimaryKey==true>
         if (StringUtils.isEmpty(dto.get${fieldItem.fieldName?cap_first}())) {
              return DataResult.fail("参数[${fieldItem.fieldName}]不能为空");
         }
</#if>
</#list>
</#if>
        LambdaQueryWrapper<${classInfo.className}Entity> queryWrapper = Wrappers.lambdaQuery();
<#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
<#list classInfo.fieldList as fieldItem >
<#if fieldItem.isPrimaryKey==true>
        queryWrapper.eq(${classInfo.className}Entity::get${fieldItem.fieldName?cap_first}, dto.get${fieldItem.fieldName?cap_first}());
</#if>
</#list>
</#if>
        ${classInfo.className}Entity entity = ${classInfo.className?uncap_first}Service.getOne(queryWrapper);;
        if (entity == null) {
            return DataResult.fail("数据不存在");
        }
        BeanUtils.copyProperties(dto, entity);
        ${classInfo.className?uncap_first}Service.updateById(entity);
        return DataResult.success();
    }


    @ApiOperation(value = "查询列表分页数据")
    @PostMapping("/listByPage")
    @RequiresPermissions("${classInfo.className?uncap_first}:listByPage")
    public DataResult listByPage(@RequestBody ${classInfo.className}VO ${classInfo.className?uncap_first}) {
        Page page = new Page(${classInfo.className?uncap_first}.getPage(), ${classInfo.className?uncap_first}.getLimit());
        ${classInfo.className}DTO dto = new ${classInfo.className}DTO();
    	BeanUtils.copyProperties(${classInfo.className?uncap_first}, dto);
        LambdaQueryWrapper<${classInfo.className}Entity> queryWrapper = Wrappers.lambdaQuery();
<#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
<#list classInfo.fieldList as fieldItem >
<#if fieldItem.isPrimaryKey==true>
        if (!ObjectUtils.isEmpty(${classInfo.className?uncap_first}.get${fieldItem.fieldName?cap_first}())) {
            queryWrapper.eq(${classInfo.className}Entity::get${fieldItem.fieldName?cap_first}, dto.get${fieldItem.fieldName?cap_first}());
        }
<#else>
        if (!ObjectUtils.isEmpty(${classInfo.className?uncap_first}.get${fieldItem.fieldName?cap_first}())) {
            queryWrapper.eq(${classInfo.className}Entity::get${fieldItem.fieldName?cap_first}, dto.get${fieldItem.fieldName?cap_first}());
        }
</#if>
</#list>
</#if>
        IPage<${classInfo.className}Entity> iPage = ${classInfo.className?uncap_first}Service.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询全部列表数据")
    @PostMapping("/list")
    @RequiresPermissions("${classInfo.className?uncap_first}:list")
    public DataResult findListByPage(@RequestBody ${classInfo.className}VO ${classInfo.className?uncap_first}) {
        LambdaQueryWrapper<${classInfo.className}Entity> queryWrapper = Wrappers.lambdaQuery();
<#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
<#list classInfo.fieldList as fieldItem >
<#if fieldItem.isPrimaryKey==true>
        if (!ObjectUtils.isEmpty(${classInfo.className?uncap_first}.get${fieldItem.fieldName?cap_first}())) {
            queryWrapper.eq(${classInfo.className}Entity::get${fieldItem.fieldName?cap_first}, ${classInfo.className?uncap_first}.get${fieldItem.fieldName?cap_first}());
        }
<#else>
        if (!ObjectUtils.isEmpty(${classInfo.className?uncap_first}.get${fieldItem.fieldName?cap_first}())) {
            queryWrapper.eq(${classInfo.className}Entity::get${fieldItem.fieldName?cap_first}, ${classInfo.className?uncap_first}.get${fieldItem.fieldName?cap_first}());
        }
</#if>
</#list>
</#if>
        List<${classInfo.className}Entity> list = ${classInfo.className?uncap_first}Service.list(queryWrapper);
        return DataResult.success(list);
    }


}

