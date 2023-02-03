/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.jun.plugin.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jun.plugin.service.SysGeneratorService;
import com.jun.plugin.utils.PageUtils;
import com.jun.plugin.utils.Query;
import com.jun.plugin.utils.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 * 
 * @author Mark sunlightcs@gmail.com
 */
@Controller
@RequestMapping("/sys/generator")
public class SysGeneratorController {
	@Autowired
	private SysGeneratorService sysGeneratorService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils pageUtil = sysGeneratorService.queryList(new Query(params));
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 生成代码
	 */
	@RequestMapping("/code")
	public void code(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String tables = request.getParameter("tables");
		String packageName = request.getParameter("packageName");
		String moduleName = request.getParameter("moduleName");
		String author = request.getParameter("author");
//		tableNames = JSON.parseArray(tables).toArray(tableNames);
		
		byte[] data = sysGeneratorService.generatorCode(tables.split(","),packageName,moduleName,author);
		
		response.reset();  
        response.setHeader("Content-Disposition", "attachment; filename=\"smart.zip\"");
        response.addHeader("Content-Length", "" + data.length);  
        response.setContentType("application/octet-stream; charset=UTF-8");  
  
        IOUtils.write(data, response.getOutputStream());  
	}
	
    /**
     * 根据表查询表字段详情
     *
     * @param tableName
     * @return
     * @author fuce
     * @Date 2019年8月15日 上午1:10:42
     */
//    @GetMapping("/queryTableInfo")
//    @ResponseBody
//    public ResultTable queryTableInfo(String tableName) {
//        List<BeanColumn> list = generatorService.queryColumns2(tableName);
//        return pageTable(list, list.size());
//    }
}
