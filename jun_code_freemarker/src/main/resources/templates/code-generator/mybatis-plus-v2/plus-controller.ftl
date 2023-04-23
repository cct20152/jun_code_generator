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
        if(this.checkExists(dto)) {
    		return DataResult.fail("同名记录信息已存在！");
    	}
        LambdaQueryWrapper<${classInfo.className}Entity> queryWrapper = Wrappers.lambdaQuery();
//        queryWrapper.eq(${classInfo.className}Entity::getValue, dto.getValue());
//        queryWrapper.eq(${classInfo.className}Entity::getId, dto.getId())
        ${classInfo.className}Entity entity = ${classInfo.className?uncap_first}Service.getOne(queryWrapper);
        if (entity != null) {
            return DataResult.fail("数据已存在");
        }
        BeanUtils.copyProperties(dto, entity);
        ${classInfo.className?uncap_first}Service.save(entity);
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
    public DataResult update(@RequestBody ${classInfo.className}Entity vo) {
    	${classInfo.className}DTO dto = new ${classInfo.className}DTO();
    	BeanUtils.copyProperties(vo, dto);
//        if (StringUtils.isEmpty(dto.getValue())) {
//            return DataResult.fail("参数不能为空");
//        }
        LambdaQueryWrapper<${classInfo.className}Entity> queryWrapper = Wrappers.lambdaQuery();
//        queryWrapper.eq(${classInfo.className}Entity::getValue, dto.getValue());
        ${classInfo.className}Entity entity = ${classInfo.className?uncap_first}Service.getById(dto.getId());
        if (entity != null) {
            return DataResult.fail("数据已存在");
        }
        BeanUtils.copyProperties(dto, entity);
        ${classInfo.className?uncap_first}Service.updateById(entity);
        return DataResult.success();
    }


    @ApiOperation(value = "查询列表数据")
    @PostMapping("/listByPage")
    @RequiresPermissions("${classInfo.className?uncap_first}:list")
    public DataResult findListByPage(@RequestBody ${classInfo.className}Entity ${classInfo.className?uncap_first}) {
        Page page = new Page(${classInfo.className?uncap_first}.getPage(), ${classInfo.className?uncap_first}.getLimit());
        if (StringUtils.isEmpty(${classInfo.className?uncap_first}.getId())) {
            return DataResult.success();
        }
        IPage<${classInfo.className}Entity> iPage = ${classInfo.className?uncap_first}Service.listByPage(page, ${classInfo.className?uncap_first}.getId());
        return DataResult.success(iPage);
    }


    @GetMapping("/queryByKey/{code}")
    @ApiOperation(value = "关键KEYWORD接口")
    @RequiresPermissions("sysDict:query")
    public DataResult querCode(@PathVariable("code") String code) {
        LambdaQueryWrapper<${classInfo.className}Entity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(${classInfo.className}Entity::getCode, code);
        wrapper.orderByAsc(${classInfo.className}Entity::getSort);
        List<${classInfo.className}Entity> result = ${classInfo.className?uncap_first}Mapper.selectList(wrapper);
        return DataResult.success(result);

    }
    
    @PostMapping("checkExists")
    @ResponseBody
    public Boolean checkExists(@RequestBody ${classInfo.className}DTO dto){
    	${classInfo.className}Entity entity = new ${classInfo.className}Entity();
    	BeanUtils.copyProperties(dto, entity);
    	LambdaQueryWrapper<${classInfo.className}Entity> queryWrapper = Wrappers.lambdaQuery();
    	queryWrapper.eq(${classInfo.className}Entity::getId, dto.getId());// 这里换成自己查询的关键条件
    	${classInfo.className}Entity one = appInfoService.getOne(queryWrapper.last("LIMIT 1"));
    	if(one == null) {
    		return false;
    	}else {
    		return true;
    	}
    }

}

