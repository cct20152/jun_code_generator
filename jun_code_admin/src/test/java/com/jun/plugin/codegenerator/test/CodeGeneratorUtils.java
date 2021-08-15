package com.jun.plugin.codegenerator.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jun.plugin.codegenerator.admin.core.model.ClassInfo;
import com.jun.plugin.codegenerator.admin.core.util.GeneratorUtils;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发。
 */
public class CodeGeneratorUtils {
	private static final Logger logger = LoggerFactory.getLogger(CodeGeneratorUtils.class);

	private static final String PROJECT_PATH = System.getProperty("user.dir");// 项目在硬盘上的基础路径
	private static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/main/resources/templates";// 模板位置

	private static final String JAVA_PATH = "/src/main/java"; // java文件路径
	private static final String RESOURCES_PATH = "/src/main/resources";// 资源文件路径

	public static void main(String[] args) throws Exception {
//        genCodeByCustomModelName("输入表名","输入自定义Model名称");
		genAllTeamplaters();
	}

	public static void genAllTeamplaters() throws Exception {
		// code generate
		List<ClassInfo> classInfos = CodeGeneratorUtils.processMetadataClassInfo(null);
		classInfos.forEach(classInfo -> {
			// code genarete
			Map<String, Object> datas = new HashMap<String, Object>();
			datas.put("classInfo", classInfo);
			// result
			Map<String, String> result = new HashMap<String, String>();
			try {
				// genProcessStringWriter(datas, result);
				String filePath = "";
				datas.put("packageController", "com.jun.plugin.biz.controller");
				datas.put("packageService", "com.jun.plugin.biz.service");
				datas.put("packageServiceImpl", "com.jun.plugin.biz.service.impl");
				datas.put("packageDao", "com.jun.plugin.biz.dao");
				datas.put("packageMybatisXML", "com.jun.plugin.biz.model");
				datas.put("packageModel", "com.jun.plugin.biz.model");

				// 生成 controller.ftl
				filePath = PROJECT_PATH + JAVA_PATH + package2Path("com.jun.plugin.biz.controller")
						+ classInfo.getClassName() + "Controller.java";
				CodeGeneratorUtils.processFile("code-generator/controller.ftl", datas, filePath);

				// 生成 service.ftl
				filePath = PROJECT_PATH + JAVA_PATH + package2Path("com.jun.plugin.biz.service")
						+ classInfo.getClassName() + "Service.java";
				CodeGeneratorUtils.processFile("code-generator/service.ftl", datas, filePath);

				// 生成 service_impl.ftl
				filePath = PROJECT_PATH + JAVA_PATH + package2Path("com.jun.plugin.biz.service.impl")
						+ classInfo.getClassName() + "ServiceImpl.java";
				CodeGeneratorUtils.processFile("code-generator/service_impl.ftl", datas, filePath);

				// 生成 dao.ftl
				filePath = PROJECT_PATH + JAVA_PATH + package2Path("com.jun.plugin.biz.dao") + classInfo.getClassName()
						+ "Dao.java";
				CodeGeneratorUtils.processFile("code-generator/dao.ftl", datas, filePath);

				// 生成 mybatis.ftl
				filePath = PROJECT_PATH + RESOURCES_PATH + "/mybatis/" + classInfo.getClassName() + ".xml";
				CodeGeneratorUtils.processFile("code-generator/mybatis.ftl", datas, filePath);

				// 生成 model.ftl
				filePath = PROJECT_PATH + JAVA_PATH + package2Path("com.jun.plugin.biz.model")
						+ classInfo.getClassName() + ".java";
				CodeGeneratorUtils.processFile("code-generator/model.ftl", datas, filePath);

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

	public static void processFile(String templateName, Map<String, Object> data, String filePath)
			throws IOException, TemplateException {
		Template template = getConfiguration().getTemplate(templateName);
		File file = new File(filePath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		template.process(data, new FileWriter(file));
		System.out.println(filePath + " 生成成功");
	}

	public static String processTemplateIntoString(Template template, Object model)
			throws IOException, TemplateException {

		StringWriter result = new StringWriter();
		template.process(model, result);
		return result.toString();
	}

	public static String processString(String templateName, Map<String, Object> params)
			throws IOException, TemplateException {
		Template template = getConfiguration().getTemplate(templateName);
		String htmlText = processTemplateIntoString(template, params);
		return htmlText;
	}

	private static freemarker.template.Configuration getConfiguration() throws IOException {
		freemarker.template.Configuration cfg = new freemarker.template.Configuration(
				freemarker.template.Configuration.VERSION_2_3_23);
		cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		return cfg;
	}

	private static String package2Path(String packageName) {
		return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
	}

	public static void genProcessStringWriter(Map<String, Object> datas, Map<String, String> result)
			throws IOException, TemplateException {
		result.put("controller_code", CodeGeneratorUtils.processString("code-generator/controller.ftl", datas));
		result.put("service_code", CodeGeneratorUtils.processString("code-generator/service.ftl", datas));
		result.put("service_impl_code", CodeGeneratorUtils.processString("code-generator/service_impl.ftl", datas));
		result.put("dao_code", CodeGeneratorUtils.processString("code-generator/dao.ftl", datas));
		result.put("mybatis_code", CodeGeneratorUtils.processString("code-generator/mybatis.ftl", datas));
		result.put("model_code", CodeGeneratorUtils.processString("code-generator/model.ftl", datas));
		System.out.println(result);
	}
	
	public static List<ClassInfo> processMetadataClassInfo(String tablename) throws IOException {
		List<ClassInfo> list = new ArrayList<ClassInfo>();
		GeneratorUtils.builderClassInfo(list);
		return list;
	}

}
