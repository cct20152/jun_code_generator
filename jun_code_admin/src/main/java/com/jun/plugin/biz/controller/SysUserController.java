package com.jun.plugin.biz.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jun.plugin.biz.model.SysUser;
import com.jun.plugin.biz.service.SysUserService;
import com.jun.plugin.codegenerator.admin.model.ReturnT;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
* 
*
* Created by wujun on '2021-09-08 11:52:01'.
*/
@Controller
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    /**
    * 新增
    */
    @RequestMapping("/insert")
    @ResponseBody
    public ReturnT<String> insert(SysUser sysUser){
        return sysUserService.insert(sysUser);
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    @ResponseBody
    public ReturnT<String> delete(int id){
        return sysUserService.delete(id);
    }

    /**
    * 更新
    */
    @RequestMapping("/update")
    @ResponseBody
    public ReturnT<String> update(SysUser sysUser){
        return sysUserService.update(sysUser);
    }

    /**
    * Load查询
    */
    @RequestMapping("/load")
    @ResponseBody
    public SysUser load(int id){
        return sysUserService.load(id);
    }

    /**
    * 分页查询
    */
    @RequestMapping("/pageList")
    @ResponseBody
    public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int offset,
                                        @RequestParam(required = false, defaultValue = "10") int pagesize) {
        return sysUserService.pageList(offset, pagesize);
    }

}
