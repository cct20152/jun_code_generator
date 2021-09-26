package com.jun.plugin.biz.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jun.plugin.biz.model.TUser;
import com.jun.plugin.biz.service.TUserService;
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
public class TUserController {

    @Resource
    private TUserService tUserService;

    /**
    * 新增
    */
    @RequestMapping("/insert")
    @ResponseBody
    public ReturnT<String> insert(TUser tUser){
        return tUserService.insert(tUser);
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    @ResponseBody
    public ReturnT<String> delete(int id){
        return tUserService.delete(id);
    }

    /**
    * 更新
    */
    @RequestMapping("/update")
    @ResponseBody
    public ReturnT<String> update(TUser tUser){
        return tUserService.update(tUser);
    }

    /**
    * Load查询
    */
    @RequestMapping("/load")
    @ResponseBody
    public TUser load(int id){
        return tUserService.load(id);
    }

    /**
    * 分页查询
    */
    @RequestMapping("/pageList")
    @ResponseBody
    public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int offset,
                                        @RequestParam(required = false, defaultValue = "10") int pagesize) {
        return tUserService.pageList(offset, pagesize);
    }

}
