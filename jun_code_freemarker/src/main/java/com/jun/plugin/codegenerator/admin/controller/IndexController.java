package com.jun.plugin.codegenerator.admin.controller;

import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jun.plugin.codegenerator.admin.core.CodeGeneratorTool;
import com.jun.plugin.codegenerator.core.model.ClassInfo;
import com.jun.plugin.codegenerator.admin.core.util.StringUtils;
import com.jun.plugin.codegenerator.admin.model.ReturnT;
import com.jun.plugin.codegenerator.admin.util.FreemarkerTool;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * sso server (for web)
 *
 */
@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Resource
    private FreemarkerTool freemarkerTool;

    @RequestMapping("/")
    public String index() {
        return "index";
    }
    @RequestMapping("/generate")
    public String index2() {
        return "generate";
    }

    @RequestMapping("/codeGenerate")
    @ResponseBody
    public ReturnT<Map<String, String>> codeGenerate(String tableSql) {

        try {

            if (tableSql==null || tableSql.trim().length()==0) {
                return new ReturnT<Map<String, String>>(ReturnT.FAIL_CODE, "表结构信息不可为空");
            }

            // parse table
            ClassInfo classInfo = CodeGeneratorTool.processTableIntoClassInfo(tableSql);

            // code genarete
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("classInfo", classInfo);
         // genProcessStringWriter(datas, result);
			params.put("packageController", "com.jun.plugin.biz.controller");
			params.put("packageService", "com.jun.plugin.biz.service");
			params.put("packageServiceImpl", "com.jun.plugin.biz.service.impl");
			params.put("packageDao", "com.jun.plugin.biz.dao");
			params.put("packageMybatisXML", "com.jun.plugin.biz.model");
			params.put("packageModel", "com.jun.plugin.biz.model");

            // result
            Map<String, String> result = new HashMap<String, String>();

            result.put("controller_code", freemarkerTool.processString("code-generator/controller.ftl", params));
            result.put("service_code", freemarkerTool.processString("code-generator/service.ftl", params));
            result.put("service_impl_code", freemarkerTool.processString("code-generator/service_impl.ftl", params));

            result.put("dao_code", freemarkerTool.processString("code-generator/dao.ftl", params));
            result.put("mybatis_code", freemarkerTool.processString("code-generator/mybatis.ftl", params));
            result.put("model_code", freemarkerTool.processString("code-generator/model.ftl", params));

            // 计算,生成代码行数
            int lineNum = 0;
            for (Map.Entry<String, String> item: result.entrySet()) {
                if (item.getValue() != null) {
                    lineNum += StringUtils.countMatches(item.getValue(), "\n");
                }
            }
            logger.info("生成代码行数：{}", lineNum);

            return new ReturnT<Map<String, String>>(result);
        } catch (IOException | TemplateException e) {
            logger.error(e.getMessage(), e);
            return new ReturnT<Map<String, String>>(ReturnT.FAIL_CODE, "表结构解析失败");
        }

    }

}