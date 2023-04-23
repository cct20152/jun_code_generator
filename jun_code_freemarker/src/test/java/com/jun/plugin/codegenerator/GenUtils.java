package com.jun.plugin.codegenerator;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.google.common.collect.Lists;
import com.jun.plugin.codegenerator.admin.core.model.ClassInfo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	

	public static Properties props = new Properties(); // 配置文件
	static {
		try {
			InputStream is = CodeGeneratorUtils.class.getClassLoader().getResourceAsStream("config.properties");
			props.load(is);
			Class.forName(props.getProperty("driver"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
	

	public static Boolean skipTables(String str) {
		str = str.toLowerCase();
		for (String x : props.getProperty("skipTable").split(",")) {
			if (str.contains(x.toLowerCase())) {
				return true;
			}
		}
		return true;
	}

	public static Boolean includeTabbles(String str) {
		str = str.toLowerCase();
		if (props.getProperty("inclueTables").equals("*")) {
			return false;
		}
		for (String x : props.getProperty("inclueTables").split(",")) {
			if (str.contains(x.toLowerCase())) {
				return false;
			}
		}
		return true;
	}

	public static void getFile(String path, List<Map<String, Object>> list) {
		File file = new File(path);
		File[] array = file.listFiles();
		for (int i = 0; i < array.length; i++) {
			if (array[i].isFile()) {
				Map<String, Object> map = new HashMap<String, Object>();
				// only take file name
				// System.out.println("^^^^^" + array[i].getName());
				// take file path and name
				// System.out.println("*****" + array[i].getPath());
				map.put(array[i].getName(), array[i].getPath());
				list.add(map);
			} else if (array[i].isDirectory()) {
				getFile(array[i].getPath(), list);
			}
		}
	}

	public static String replace_(String str) {
		// 根据下划线分割
		String[] split = str.split("_");
		// 循环组装
		String result = split[0];
		if (split.length > 1) {
			for (int i = 1; i < split.length; i++) {
				result += firstUpper(split[i]);
			}
		}
		return result;
	}

	public static String firstUpper(String str) {
		// log.info("str:"+str+",str.length="+str.length());
		if (!org.springframework.util.StringUtils.isEmpty(str)) {
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		} else {
			return str;
		}
	}

	public static String firstLower(String str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

	public static String replaceTabblePreStr(String str) {
		return str.replaceFirst("tab_", "").replaceFirst("tb_", "");
	}

	public static String replaceRow(String str) {
		str = str.toLowerCase().replaceFirst("tab_", "").replaceFirst("tb_", "").replaceFirst("t_", "");
		for (String x : props.getProperty("rowRemovePrefixes").split(",")) {
			str = str.replaceFirst(x.toLowerCase(), "");
		}
		return str;
	}

	public static String simpleNameLowerFirst(String type) {
		// 去掉前缀
		type = simpleName(type);
		// 将第一个字母转成小写
		return GenUtils.firstLower(type);
	}

	public static String simpleName(String type) {
		return type.replace("java.lang.", "").replaceFirst("java.util.", "");
	}

	public static String upperCaseFirstWord(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static String getType(int value) {
		switch (value) {
		case -6:
			return "java.lang.Integer";
		case 5:
			return "java.lang.Integer";
		case 4:
			return "java.lang.Integer";
		case -5:
			return "java.lang.Long";
		case 6:
			return "java.lang.Float";
		case 8:
			return "java.lang.Double";
		case 1:
			return "java.lang.String";
		case 12:
			return "java.lang.String";
		case -1:
			return "java.lang.String";
		case 91:
			return "java.util.Date";
		case 92:
			return "java.util.Date";
		case 93:
			return "java.util.Date";
		case 16:
			return "java.lang.Boolean";
		default:
			return "java.lang.String";
		}
	}

	/***
	 * 构建 Java文件，遍历文件夹下所有的模板，然后生成对应的文件（需要配置模板的package及path）
	 */
	public static void batchBuilderByDirectory1111(Map<String, Object> modelMap) {
		List<Map<String, Object>> srcFiles = new ArrayList<Map<String, Object>>();
		String TEMPLATE_PATH = CodeGeneratorUtils.class.getClassLoader().getResource("").getPath()
		.replace("/target/classes/", "") + "/src/main/resources/" + props.getProperty("template_path");
		getFile(TEMPLATE_PATH, srcFiles);
		for (int i = 0; i < srcFiles.size(); i++) {
			HashMap<String, Object> m = (HashMap<String, Object>) srcFiles.get(i);
			Set<String> set = m.keySet();
			Iterator<String> it = set.iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				if ((boolean) modelMap.get("Swagger") == true) {
					if (!key.contains(".json")) {
						continue;
					}
				}
				String templateFileName = key;
				String templateFileNameSuffix = key.substring(key.lastIndexOf("."), key.length());
				String templateFileNamePrefix = key.substring(0, key.lastIndexOf("."));
				String templateFilePathAndName = String.valueOf(m.get(key));
				String templateFilePath = templateFilePathAndName.replace("\\" + templateFileName, "");
				String templateFilePathMiddle = "";
				if (!templateFilePath.endsWith(props.getProperty("template_path").replace("/", "\\"))) {
					templateFilePathMiddle = templateFilePath
							.substring(templateFilePath.lastIndexOf("\\"), templateFilePath.length()).replace("\\", "");
				}
				if (key.contains(".json")) {
					//logger.info("templateFilePath=" + templateFilePath);
					continue;
				}
				try {
					String path = null;
					if (templateFileNameSuffix.equalsIgnoreCase(".java")) {
						// 创建文件夹
						path = GenUtils.PROJECT_PATH + "/" + props.getProperty("basePackage").replace(".", "/")
								+ "/" + templateFileNamePrefix.toLowerCase();
					}
					if (templateFileNameSuffix.equalsIgnoreCase(".ftl")) {
						path = GenUtils.PROJECT_PATH + "/" + props.getProperty("basePackage").replace(".", "/")
								+ "/" + templateFilePathMiddle + "/";
					}
					String fileNameNew = templateFileNamePrefix
							.replace("${className}", String.valueOf(modelMap.get("Table")))
							.replace("${classNameLower}", String.valueOf(modelMap.get("Table")).toLowerCase());
					// 创建文件
//					GeneratorUtils.writer(template, modelMap, path + "/" + fileNameNew);
					CodeGeneratorUtils.processFile(templateFileName, modelMap, path + "/" + fileNameNew);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}

	public static void showTableInfo1111(ResultSet tableResultSet) throws SQLException {
		System.out.println(tableResultSet.getString("TABLE_CAT"));
		System.out.println(tableResultSet.getString("TABLE_SCHEM"));
		System.out.println(tableResultSet.getString("TABLE_NAME"));
		System.out.println(tableResultSet.getString("TABLE_TYPE"));
		System.out.println(tableResultSet.getString("REMARKS"));
	}

	public static void showColumnInfo1111(ResultSet cloumnsSet) throws SQLException {
		System.out.println("TABLE_CAT is :" + cloumnsSet.getString("TABLE_CAT"));
		System.out.println("TABLE_SCHEM is :" + cloumnsSet.getString("TABLE_SCHEM"));
		System.out.println("TABLE_NAME is :" + cloumnsSet.getString("TABLE_NAME"));
		System.out.println("COLUMN_NAME is :" + cloumnsSet.getString("COLUMN_NAME"));
		System.out.println("DATA_TYPE is :" + cloumnsSet.getInt("DATA_TYPE"));
		System.out.println("TYPE_NAME is :" + cloumnsSet.getString("TYPE_NAME"));
		System.out.println("COLUMN_SIZE is :" + cloumnsSet.getInt("COLUMN_SIZE"));
		System.out.println("BUFFER_LENGTH is :" + cloumnsSet.getInt("BUFFER_LENGTH"));
		System.out.println("DECIMAL_DIGITS is :" + cloumnsSet.getInt("DECIMAL_DIGITS"));
		System.out.println("NUM_PREC_RADIX is :" + cloumnsSet.getInt("NUM_PREC_RADIX"));
		System.out.println("NULLABLE is :" + cloumnsSet.getInt("NULLABLE"));
		System.out.println("REMARKS is :" + cloumnsSet.getString("REMARKS"));
		System.out.println("COLUMN_DEF is :" + cloumnsSet.getString("COLUMN_DEF"));
		System.out.println("SQL_DATA_TYPE is :" + cloumnsSet.getInt("SQL_DATA_TYPE"));
		System.out.println("SQL_DATETIME_SUB is :" + cloumnsSet.getInt("SQL_DATETIME_SUB"));
		System.out.println("CHAR_OCTET_LENGTH is :" + cloumnsSet.getInt("CHAR_OCTET_LENGTH"));
		System.out.println("ORDINAL_POSITION is :" + cloumnsSet.getInt("ORDINAL_POSITION"));
		System.out.println("IS_NULLABLE is :" + cloumnsSet.getString("IS_NULLABLE"));
	}
    
}
