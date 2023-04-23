package com.jun.plugin.codegenerator;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.google.common.collect.Lists;
import com.jun.plugin.codegenerator.admin.core.model.ClassInfo;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.StringUtils;



/**
 * 代码生成器 工具类
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
public class GenUtils {
	
	public static final String PROJECT_PATH = System.getProperty("user.dir");// 项目在硬盘上的基础路径，项目路径
	public static final String JAVA_PATH = "/src/main/java"; // java文件路径
	public static final String RESOURCES_PATH = "/src/main/resources";// 资源文件路径
	public static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/main/resources/templates";// 模板位置

    public static List<String> getTemplates() {
        List<String> templates = Lists.newArrayList();
        templates.add("code-generator/controller.ftl");
        templates.add("code-generator/service.ftl");
        templates.add("code-generator/service_impl.ftl");
        templates.add("code-generator/dao.ftl");
        templates.add("code-generator/mybatis.ftl");
        templates.add("code-generator/model.ftl");
//        templates.add("code-generator/menu.sql.vm");
//        templates.add("code-generator/list.html.vm");

        return templates;
    }
    public static Map<String, Object> getPackages() {
    	Map<String, Object> datas = new HashMap<String, Object>();
    	datas.put("packageController", "com.jun.plugin.biz.controller");
		datas.put("packageService", "com.jun.plugin.biz.service");
		datas.put("packageServiceImpl", "com.jun.plugin.biz.service.impl");
		datas.put("packageDao", "com.jun.plugin.biz.dao");
		datas.put("packageMybatisXML", "com.jun.plugin.biz.model");
		datas.put("packageModel", "com.jun.plugin.biz.model");
    	return datas;
    }
    
    public static List<String> getFilePaths(ClassInfo classInfo) {
        List<String> filePaths = new ArrayList<>();
        filePaths.add(PROJECT_PATH + JAVA_PATH + package2Path("com.jun.plugin.biz.controller") + classInfo.getClassName() + "Controller.java");
        filePaths.add(PROJECT_PATH + JAVA_PATH + package2Path("com.jun.plugin.biz.service") + classInfo.getClassName() + "Service.java");
		filePaths.add(PROJECT_PATH + JAVA_PATH + package2Path("com.jun.plugin.biz.service.impl") + classInfo.getClassName() + "ServiceImpl.java");
		filePaths.add(PROJECT_PATH + JAVA_PATH + package2Path("com.jun.plugin.biz.dao") + classInfo.getClassName() + "Dao.java");
		filePaths.add(PROJECT_PATH + RESOURCES_PATH + "/mybatis/" + classInfo.getClassName() + ".xml");
		filePaths.add(PROJECT_PATH + JAVA_PATH + package2Path("com.jun.plugin.biz.model") + classInfo.getClassName() + ".java");
        return filePaths;
    }
    
	private static String package2Path(String packageName) {
		return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
	}
    
}
