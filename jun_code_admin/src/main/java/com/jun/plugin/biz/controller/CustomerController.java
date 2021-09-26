package com.jun.plugin.biz.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jun.plugin.biz.model.Customer;
import com.jun.plugin.biz.service.CustomerService;
import com.jun.plugin.codegenerator.admin.model.ReturnT;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
* 客户资料
*
* Created by wujun on '2021-09-08 11:52:01'.
*/
@Controller
public class CustomerController {

    @Resource
    private CustomerService customerService;

    /**
    * 新增
    */
    @RequestMapping("/insert")
    @ResponseBody
    public ReturnT<String> insert(Customer customer){
        return customerService.insert(customer);
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    @ResponseBody
    public ReturnT<String> delete(int id){
        return customerService.delete(id);
    }

    /**
    * 更新
    */
    @RequestMapping("/update")
    @ResponseBody
    public ReturnT<String> update(Customer customer){
        return customerService.update(customer);
    }

    /**
    * Load查询
    */
    @RequestMapping("/load")
    @ResponseBody
    public Customer load(int id){
        return customerService.load(id);
    }

    /**
    * 分页查询
    */
    @RequestMapping("/pageList")
    @ResponseBody
    public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int offset,
                                        @RequestParam(required = false, defaultValue = "10") int pagesize) {
        return customerService.pageList(offset, pagesize);
    }

}
