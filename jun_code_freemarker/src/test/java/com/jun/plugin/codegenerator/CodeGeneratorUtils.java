package com.jun.plugin.codegenerator;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.jun.plugin.codegenerator.admin.core.model.ClassInfo;

import freemarker.template.TemplateException;

/**
 * 代码生成器，根据DatabaseMetaData及数据表名称生成对应的Model、Mapper、Service、Controller简化基础代码逻辑开发。
 * 
 * @author Wujun
 */
public class CodeGeneratorUtils {
	private static final Logger logger = LoggerFactory.getLogger(CodeGeneratorUtils.class);
	
	public static void main(String[] args) throws Exception {
		String tables = "t_basc,t_basc_arg";
//		String tables = "git_user";
//		String tables = "app_infoenvt,app_member,app_datasource,app_git_config,git_user,app_deploy_config";
		genTables(tables.split(","));
	}


	public static void genTables(String [] tables) throws Exception {
		List<ClassInfo> classInfos = GenUtils.getClassInfo(tables);
		classInfos.forEach(classInfo -> {
			Map<String, Object> datas = new HashMap<String, Object>();
			datas.put("classInfo", classInfo);
//			datas.putAll(GenUtils.getPackages());
			datas.put("authorName","wujun");
			datas.put("isLombok",true);
			datas.put("isAutoImport",true);
			datas.put("isWithPackage",true);
			datas.put("isSwagger",true);
			datas.put("isComment",true);
			datas.put("packageName",GenUtils.PACKAGE);
			Map<String, String> result = new HashMap<String, String>();
			try {
				//GenUtils.processTemplatesStringWriter(datas, result);
				GenUtils.processTemplates(classInfo, datas,getTemplates());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TemplateException e) {
				e.printStackTrace();
			}
			// 计算,生成代码行数
			int lineNum = 0;
			for (Map.Entry<String, String> item : result.entrySet()) {
				if (item.getValue() != null) {
					lineNum += StringUtils.countMatches(item.getValue(), "\n");
				}
			}
			logger.info("生成代码行数：{}", lineNum);
		});

	}
	
	
	public static List<String> getTemplates() {
        List<String> templates = Lists.newArrayList();
//        templates.add("code-generator/controller.ftl");
//        templates.add("code-generator/service.ftl");
//        templates.add("code-generator/service_impl.ftl");
//        templates.add("code-generator/dao.ftl");
//        templates.add("code-generator/mybatis.ftl");
//        templates.add("code-generator/model.ftl");
        // ************************************************************************************
        templates.add("code-generator/mybatis-plus-v2/plus-controller.ftl");
        templates.add("code-generator/mybatis-plus-v2/plus-entity.ftl");
        templates.add("code-generator/mybatis-plus-v2/plus-mapper.ftl");
        templates.add("code-generator/mybatis-plus-v2/plus-service.ftl");
        templates.add("code-generator/mybatis-plus-v2/plus-dto.ftl");
        templates.add("code-generator/mybatis-plus-v2/plus-vo.ftl");
        templates.add("code-generator/mybatis-plus-v2/plus-serviceimpl.ftl");
        return templates;
    }
//    public static Map<String, Object> getPackages() {
//    	Map<String, Object> datas = new HashMap<String, Object>();
////    	datas.put("packageController", "com.jun.plugin.biz.controller");
////		datas.put("packageService", "com.jun.plugin.biz.service");
////		datas.put("packageServiceImpl", "com.jun.plugin.biz.service.impl");
////		datas.put("packageDao", "com.jun.plugin.biz.dao");
////		datas.put("packageMybatisXML", "com.jun.plugin.biz.model");
////		datas.put("packageModel", "com.jun.plugin.biz.model");
//    	// ************************************************************************************
//    	datas.put("packageController", "com.jun.plugin.biz.controller");
//    	datas.put("packageModel", "com.jun.plugin.biz.entity");
//    	datas.put("packageMapper", "com.jun.plugin.biz.mapper");
//		datas.put("packageService", "com.jun.plugin.biz.service");
//		datas.put("packageDTO", "com.jun.plugin.biz.dto");
//		datas.put("packageVO", "com.jun.plugin.biz.vo");
//		datas.put("packageService", "com.jun.plugin.biz.service.impl");
//    	return datas;
//    }

}
