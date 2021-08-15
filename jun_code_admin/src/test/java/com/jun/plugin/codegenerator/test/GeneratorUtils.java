package com.jun.plugin.codegenerator.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.CaseFormat;
import com.jun.plugin.codegenerator.admin.controller.IndexController;
import com.jun.plugin.codegenerator.admin.core.CodeGeneratorTool;
import com.jun.plugin.codegenerator.admin.core.model.ClassInfo;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发。
 */
public class GeneratorUtils {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
    //JDBC配置，请修改为你项目的实际配置
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/db_api?useUnicode=true&characterEncoding=UTF-8&useSSL=true&serverTimezone=UTC&useInformationSchema=true";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "";
    //mysql  5.7  com.mysql.jdbc.Driver  ,mysql 8.0  com.mysql.cj.jdbc.Driver
    private static final String JDBC_DIVER_CLASS_NAME = "com.mysql.jdbc.Driver";  

    private static final String PROJECT_PATH = System.getProperty("user.dir");//项目在硬盘上的基础路径
    private static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/main/resources/templates";//模板位置

    private static final String JAVA_PATH = "/src/main/java"; //java文件路径
    private static final String RESOURCES_PATH = "/src/main/resources";//资源文件路径

//    private static final String PACKAGE_PATH = package2Path("com.jun.plugin.biz");//生成的Service存放路径
    private static final String PACKAGE_PATH_SERVICE = package2Path("com.jun.plugin.biz.service");//生成的Service存放路径
    private static final String PACKAGE_PATH_SERVICE_IMPL = package2Path("com.jun.plugin.biz.service.impl");//生成的Service实现存放路径
//    private static final String PACKAGE_PATH_CONTROLLER = package2Path("com.jun.plugin.biz.controller");//生成的Controller存放路径

    private static final String AUTHOR = "Wujun";//@author
    private static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());//@date

    public static void main(String[] args) throws Exception {
//        genCode("customer");
//        genCodeByCustomModelName("输入表名","输入自定义Model名称");
    	genAllTeamplaters();
    }

    /**
     * 通过数据表名称生成代码，Model 名称通过解析数据表名称获得，下划线转大驼峰的形式。
     * 如输入表名称 "t_user_detail" 将生成 TUserDetail、TUserDetailMapper、TUserDetailService ...
     * @param tableNames 数据表名称...
     * @throws Exception 
     */
    public static void genCode(String... tableNames) throws Exception {
        for (String tableName : tableNames) {
            genCodeByCustomModelName(tableName, null);
        }
    }

    /**
     * 通过数据表名称，和自定义的 Model 名称生成代码
     * 如输入表名称 "t_user_detail" 和自定义的 Model 名称 "User" 将生成 User、UserMapper、UserService ...
     * @param tableName 数据表名称
     * @param modelName 自定义的 Model 名称
     * @throws Exception 
     */
    public static void genCodeByCustomModelName(String tableName, String modelName) throws Exception {
//        genService(tableName, modelName);
        
    }


    public static void genService(String tableName, String modelName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", tableNameConvertLowerCamel(tableName));
//            data.put("basePackage", BASE_PACKAGE);
//            data.put("baseImportPackage", BASE_IMPORT_PACKAGE);

            File file = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE + modelNameUpperCamel + "Service.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("service.ftl").process(data,
                    new FileWriter(file));
            System.out.println(modelNameUpperCamel + "Service.java 生成成功");

            File file1 = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE_IMPL + modelNameUpperCamel + "ServiceImpl.java");
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            cfg.getTemplate("service-impl.ftl").process(data,
                    new FileWriter(file1));
            System.out.println(modelNameUpperCamel + "ServiceImpl.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Service失败", e);
        }
    }
    
    public static void genAllTeamplaters() throws Exception {

		// code generate
		List<ClassInfo> classInfos = CodeGeneratorTool.processMetadataClassInfo(null);
		classInfos.forEach(classInfo -> {
			// code genarete
			Map<String, Object> datas = new HashMap<String, Object>();
			datas.put("classInfo", classInfo);
			// result
			Map<String, String> result = new HashMap<String, String>();
			try {
//				result.put("controller_code", GeneratorUtils.processString("code-generator/controller.ftl", datas));
//				result.put("service_code", GeneratorUtils.processString("code-generator/service.ftl", datas));
//				result.put("service_impl_code", GeneratorUtils.processString("code-generator/service_impl.ftl", datas));
//				result.put("dao_code", GeneratorUtils.processString("code-generator/dao.ftl", datas));
//				result.put("mybatis_code", GeneratorUtils.processString("code-generator/mybatis.ftl", datas));
//				result.put("model_code", GeneratorUtils.processString("code-generator/model.ftl", datas));
//				System.out.println(result);
				String filePath = "";
				datas.put("packageController","com.jun.plugin.biz.controller");
				datas.put("packageService","com.jun.plugin.biz.service");
				datas.put("packageServiceImpl","com.jun.plugin.biz.service.impl");
				datas.put("packageDao","com.jun.plugin.biz.dao");
				datas.put("packageMybatisXML","com.jun.plugin.biz.model");
				datas.put("packageModel","com.jun.plugin.biz.model");
				
				// 生成  controller.ftl
				filePath = PROJECT_PATH + JAVA_PATH + package2Path("com.jun.plugin.biz.controller") + classInfo.getClassName()+"Controller.java";
				GeneratorUtils.processFile("code-generator/controller.ftl", datas, filePath);
				
				// 生成  service.ftl
				filePath = PROJECT_PATH + JAVA_PATH + package2Path("com.jun.plugin.biz.service") + classInfo.getClassName()+"Service.java";
				GeneratorUtils.processFile("code-generator/service.ftl", datas, filePath);
				
				// 生成  service_impl.ftl
				filePath = PROJECT_PATH + JAVA_PATH + package2Path("com.jun.plugin.biz.service.impl") + classInfo.getClassName()+"ServiceImpl.java";
				GeneratorUtils.processFile("code-generator/service_impl.ftl", datas, filePath);
				
				// 生成  dao.ftl
				filePath = PROJECT_PATH + JAVA_PATH + package2Path("com.jun.plugin.biz.dao") + classInfo.getClassName()+"Dao.java";
				GeneratorUtils.processFile("code-generator/dao.ftl", datas, filePath);
				
				// 生成  mybatis.ftl
				filePath = PROJECT_PATH + RESOURCES_PATH + "/mybatis/" + classInfo.getClassName()+".xml";
				GeneratorUtils.processFile("code-generator/mybatis.ftl", datas, filePath);

		    	// 生成  model.ftl
		    	filePath = PROJECT_PATH + JAVA_PATH + package2Path("com.jun.plugin.biz.model") + classInfo.getClassName()+".java";
				GeneratorUtils.processFile("code-generator/model.ftl", datas, filePath);
				
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
    
    /**
     * 
     * @param templateName  模板名称
     * @param data  模板数据
     * @param fileName  文件名称
     * @param packagePath  文件package路径，相对路径
     * @throws IOException
     * @throws TemplateException
     */
    public static void processFile(String templateName, Map<String, Object> data,String filePath)
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
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    private static String tableNameConvertLowerCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
    }

    private static String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());

    }

    private static String tableNameConvertMappingPath(String tableName) {
        tableName = tableName.toLowerCase();//兼容使用大写的表名
        return "/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
    }

    private static String modelNameConvertMappingPath(String modelName) {
        String tableName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, modelName);
        return tableNameConvertMappingPath(tableName);
    }

    private static String package2Path(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }

}
