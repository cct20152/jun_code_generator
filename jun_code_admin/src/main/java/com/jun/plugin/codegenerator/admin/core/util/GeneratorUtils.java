package com.jun.plugin.codegenerator.admin.core.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

import com.jun.plugin.codegenerator.admin.core.model.ClassInfo;
import com.jun.plugin.codegenerator.admin.core.model.FieldInfo;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class GeneratorUtils {

	public static Logger loggger = Logger.getLogger(GeneratorUtils.class.toString());

	public static void main(String[] args) throws IOException {
		// 调用该方法即可
		loggger.info("开始生成代码");
		List<ClassInfo> list = new ArrayList<ClassInfo>();
		GeneratorUtils.builderClassInfo(list);
		// 打开文件夹
//        Runtime.getRuntime().exec("cmd.exe /c start "+GenUtils.PROJECT_PATH + GenUtils.PACKAGE_BASE.replace(".", "/"));
		loggger.info("代码生成完成");
	}

	// 配置文件
	private static Properties props = new Properties();

	// pojoPackage
	public static String PACKAGE_POJO;

	// mapperPackage
	public static String PACKAGE_MAPPER;

	// pojoPackage
	public static String PACKAGE_BASE;

	// serviceInterfacePackage
	public static String PACKAGE_SERVICE_INTERFACE;

	// serviceInterfaceImplPackage
	public static String PACKAGE_SERVICE_INTERFACE_IMPL;

	// controllerPackage
	public static String PACKAGE_CONTROLLER;

	// feignPackage
	public static String PACKAGE_FEIGN;

	// 数据库账号
	public static String UNAME;

	// 项目路径
	public static String PROJECT_PATH;

	// 是否使用swagger
	public static Boolean SWAGGER;

	// 服务名字
	public static String SERVICENAME;

	// swagger-ui路径
	public static String SWAGGERUI_PATH;

	public static String OUTROOT;
	public static String TEMPLATE_PATH;
	public static String TEMPLATE_NAME;
	public static String TABLEREMOVEPREFIXES;
	public static String ROWREMOVEPREFIXES;
	public static String SKIPTABLE;
	public static String INCLUETABLES;

	static {
		try {
			// 加载配置文件
			InputStream is = GeneratorUtils.class.getClassLoader().getResourceAsStream("config.properties");

			// 创建Properties对象
			props.load(is);
			PACKAGE_BASE = props.getProperty("basePackage");
			TEMPLATE_PATH = GeneratorUtils.class.getClassLoader().getResource("").getPath().replace("/target/classes/",
					"") + "/src/main/resources/" + props.getProperty("template_path");
			TEMPLATE_NAME = props.getProperty("template_path");
			TABLEREMOVEPREFIXES = props.getProperty("tableRemovePrefixes");
			ROWREMOVEPREFIXES = props.getProperty("rowRemovePrefixes");
			SKIPTABLE = props.getProperty("skipTable");
			INCLUETABLES = props.getProperty("inclueTables");

			// 获取对应的配置信息
			PACKAGE_POJO = props.getProperty("pojoPackage");
			PACKAGE_MAPPER = props.getProperty("mapperPackage");
			PACKAGE_SERVICE_INTERFACE = props.getProperty("serviceInterfacePackage");
			PACKAGE_SERVICE_INTERFACE_IMPL = props.getProperty("serviceInterfaceImplPackage");
			PACKAGE_CONTROLLER = props.getProperty("controllerPackage");
			PACKAGE_FEIGN = props.getProperty("feignPackage");
			UNAME = props.getProperty("uname");
			SWAGGER = Boolean.valueOf(props.getProperty("enableSwagger"));
			SERVICENAME = props.getProperty("serviceName");
			SWAGGERUI_PATH = props.getProperty("swaggeruipath");
			// 工程路径
			PROJECT_PATH = GeneratorUtils.class.getClassLoader().getResource("").getPath().replace("/target/classes/",
					"") + "/src/main/java/";

			// 加载数据库驱动
			Class.forName(props.getProperty("driver"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * 模板构建
	 */
	public static void builderClassInfo(List<ClassInfo> list) {
		try {
			// 获取数据库连接
			Connection conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("uname"),
					props.getProperty("pwd"));
			DatabaseMetaData metaData = conn.getMetaData();

			// 获取数据库类型：MySQL
			String databaseType = metaData.getDatabaseProductName();

			// 针对MySQL数据库进行相关生成操作
			if (databaseType.equals("MySQL")) {
				// 获取所有表结构
				ResultSet tableResultSet = metaData.getTables(null, "%", "%", new String[] { "TABLE" });

				// 获取数据库名字
				String database = conn.getCatalog();

				// 循环所有表信息
				while (tableResultSet.next()) {

					String tableName = tableResultSet.getString("TABLE_NAME"); // 获取表名
					String table = GeneratorUtils.replace_(GeneratorUtils.replaceTab(tableName)); // 名字操作,去掉tab_,tb_，去掉_并转驼峰
					String Table = GeneratorUtils.firstUpper(table); // 获取表名,首字母大写
					String tableComment = tableResultSet.getString("REMARKS"); // 获取表备注
					String className = GeneratorUtils.replace_(GeneratorUtils.replaceTab(tableName)); // 名字操作,去掉tab_,tb_，去掉_并转驼峰
					String classNameFirstUpper = GeneratorUtils.firstUpper(className); // 大写对象

//					showTableInfo(tableResultSet); 

					loggger.info("当前表名：" + tableName);

					Set<String> typeSet = new HashSet<String>(); // 所有需要导包的类型
					ResultSet cloumnsSet = metaData.getColumns(database, UNAME, tableName, null); // 获取表所有的列
					ResultSet keySet = metaData.getPrimaryKeys(database, UNAME, tableName); // 获取主键
					String key = "", keyType = "";
					while (keySet.next()) {
						key = keySet.getString(4);
					}
					// V1 初始化数据及对象 模板V1 field List
					List<FieldInfo> fieldList = new ArrayList<FieldInfo>();

					while (cloumnsSet.next()) {
						String remarks = cloumnsSet.getString("REMARKS");// 列的描述
						String columnName = cloumnsSet.getString("COLUMN_NAME"); // 获取列名
						String javaType = GeneratorUtils.getType(cloumnsSet.getInt("DATA_TYPE"));// 获取类型，并转成JavaType
						int COLUMN_SIZE = cloumnsSet.getInt("COLUMN_SIZE");// 获取
						String TABLE_SCHEM = cloumnsSet.getString("TABLE_SCHEM");// 获取
						String COLUMN_DEF = cloumnsSet.getString("COLUMN_DEF");// 获取
						int NULLABLE = cloumnsSet.getInt("NULLABLE");// 获取
//						showColumnInfo(cloumnsSet);
						String propertyName = GeneratorUtils.replace_(GeneratorUtils.replaceRow(columnName));// 处理列名，驼峰
						typeSet.add(javaType);// 需要导包的类型
						if (columnName.equals(key)) {
							keyType = GeneratorUtils.simpleName(javaType);// 主键类型,单主键支持
						}

						// V1 初始化数据及对象
						FieldInfo fieldInfo = new FieldInfo();
						fieldInfo.setColumnName(columnName);
						fieldInfo.setFieldName(propertyName);
						fieldInfo.setFieldClass(GeneratorUtils.simpleName(javaType));
						fieldInfo.setFieldComment(remarks);
						fieldList.add(fieldInfo);

					}
					// ************************************************************************
					if (fieldList != null && fieldList.size() > 0) {
						ClassInfo classInfo = new ClassInfo();
						classInfo.setTableName(tableName);
						classInfo.setClassName(classNameFirstUpper);
						classInfo.setClassComment(tableComment);
						classInfo.setFieldList(fieldList);
						list.add(classInfo);
					}
					// ************************************************************************
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void showTableInfo(ResultSet tableResultSet) throws SQLException {
		System.out.println(tableResultSet.getString("TABLE_CAT"));
		System.out.println(tableResultSet.getString("TABLE_SCHEM"));
		System.out.println(tableResultSet.getString("TABLE_NAME"));
		System.out.println(tableResultSet.getString("TABLE_TYPE"));
		System.out.println(tableResultSet.getString("REMARKS"));
	}

	public static void showColumnInfo(ResultSet cloumnsSet) throws SQLException {
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

	 

	/***
	 * 构建Controller
	 * 
	 * @param modelMap
	 */
	public static void builder(Map<String, Object> modelMap, // 数据模型
			String templatePath, // 模板路径
			String templateFile, // 模板文件
			String storePath, // 存储路径
			String suffix) { // 生成文件后缀名字
		try {
			// 获取模板对象
			Template template = GeneratorUtils.loadTemplate(GeneratorUtils.class.getResource(templatePath).getPath(),
					templateFile);

			// 创建文件夹
			String path = GeneratorUtils.PROJECT_PATH + storePath.replace(".", "/");
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}

			// 创建文件
			GeneratorUtils.writer(template, modelMap, path + "/" + modelMap.get("Table") + suffix);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * 创建模板对象
	 * 
	 * @param path
	 * @param ftl
	 * @return
	 * @throws Exception
	 */
	public static Template loadTemplate(String path, String ftl) throws Exception {
		// 第一步：创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对于的版本号。
		Configuration configuration = new Configuration(Configuration.getVersion());
		// 第二步：设置模板文件所在的路径。
		configuration.setDirectoryForTemplateLoading(new File(path));
		// 第三步：设置模板文件使用的字符集。一般就是utf-8.
		configuration.setDefaultEncoding("utf-8");
		// 第四步：加载一个模板，创建一个模板对象。
		Template template = configuration.getTemplate(ftl);
		return template;
	}

	/***
	 * 输出文件
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void writer(Template template, Map dataModel, String file) throws Exception {
		// 包参数
		dataModel.put("package_controller", GeneratorUtils.PACKAGE_CONTROLLER);
		dataModel.put("package_pojo", GeneratorUtils.PACKAGE_POJO);
		dataModel.put("package_mapper", GeneratorUtils.PACKAGE_MAPPER);
		dataModel.put("package_service", GeneratorUtils.PACKAGE_SERVICE_INTERFACE);
		dataModel.put("package_service_impl", GeneratorUtils.PACKAGE_SERVICE_INTERFACE_IMPL);

		// 创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名。
		Writer out = new FileWriter(new File(file));
		// 调用模板对象的process方法输出文件。
		template.process(dataModel, out);
		// 关闭流。
		out.close();
	}

	// new builder 2021-08-14
	public static void builder(Map<String, Object> modelMap, String templatePath, String templateFile) {
		String templateName = templateFile.substring(0, templateFile.lastIndexOf("."));
		String storePath = GeneratorUtils.PACKAGE_BASE + "." + templateName; // 存储路径
		String suffix = templateName + ".java"; // 生成文件后缀名字
		GeneratorUtils.builder(modelMap, templatePath, templateFile, storePath, suffix);
	}

	/***
	 * 
	 * @param dataModel
	 */
	public static void batchBuilderAll(Map<String, Object> modelMap) {
		// 生成Controller层文件
		GeneratorUtils.builder(modelMap, "/template/controller", "Controller.java", GeneratorUtils.PACKAGE_CONTROLLER,
				"Controller.java");
		// 生成Service层文件
		GeneratorUtils.builder(modelMap, "/template/service", "Service.java", GeneratorUtils.PACKAGE_SERVICE_INTERFACE,
				"Service.java");
		// 生成ServiceImpl层文件
		GeneratorUtils.builder(modelMap, "/template/service/impl", "ServiceImpl.java",
				GeneratorUtils.PACKAGE_SERVICE_INTERFACE_IMPL, "ServiceImpl.java");
		// 生成Dao层文件
		GeneratorUtils.builder(modelMap, "/template/dao", "Mapper.java", GeneratorUtils.PACKAGE_MAPPER, "Mapper.java");
		// 生成Pojo层文件
		GeneratorUtils.builder(modelMap, "/template/pojo", "Pojo.java", GeneratorUtils.PACKAGE_POJO, ".java");
	}

	/***
	 * 构建
	 * 
	 * @param modelMap
	 */
	public static void batchBuilder(Map<String, Object> modelMap) {
		// 生成Controller层文件
		GeneratorUtils.builder(modelMap, "/template_v1/controller", "Controller.java");
		// 生成Dao层文件
		GeneratorUtils.builder(modelMap, "/template_v1/dao", "Mapper.java");
		// 生成Feign层文件
		GeneratorUtils.builder(modelMap, "/template_v1/feign", "Feign.java");
		// 生成Pojo层文件
		GeneratorUtils.builder(modelMap, "/template_v1/pojo", "Pojo.java");
		// 生成Service层文件
		GeneratorUtils.builder(modelMap, "/template_v1/service", "Service.java");
		// 生成ServiceImpl层文件
		GeneratorUtils.builder(modelMap, "/template_v1/service/impl", "ServiceImpl.java");

	}

	/***
	 * 构建 Java文件
	 * 
	 * @param modelMap
	 */
	public static void batchBuilderV2(Map<String, Object> modelMap) {
		List<Map<String, Object>> srcFiles = new ArrayList<Map<String, Object>>();
		getFile(GeneratorUtils.TEMPLATE_PATH, srcFiles);

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
				if (!templateFilePath.endsWith(GeneratorUtils.TEMPLATE_NAME.replace("/", "\\"))) {
					templateFilePathMiddle = templateFilePath
							.substring(templateFilePath.lastIndexOf("\\"), templateFilePath.length()).replace("\\", "");
				}
				if (key.contains(".json")) {
					loggger.info("templateFilePath=" + templateFilePath);
					continue;
				}
				try {
					// 获取模板对象
					Template template = GeneratorUtils.loadTemplate(templateFilePath, templateFileName);
					String path = null;
					if (templateFileNameSuffix.equalsIgnoreCase(".java")) {
						// 创建文件夹
						path = GeneratorUtils.PROJECT_PATH + "/" + GeneratorUtils.PACKAGE_BASE.replace(".", "/") + "/"
								+ templateFileNamePrefix.toLowerCase();
					}
					if (templateFileNameSuffix.equalsIgnoreCase(".ftl")) {
						path = GeneratorUtils.PROJECT_PATH + "/" + GeneratorUtils.PACKAGE_BASE.replace(".", "/") + "/"
								+ templateFilePathMiddle + "/";
					}
					File file = new File(path);
					if (!file.exists()) {
						file.mkdirs();
					}
					String fileNameNew = templateFileNamePrefix
							.replace("${className}", String.valueOf(modelMap.get("Table")))
							.replace("${classNameLower}", String.valueOf(modelMap.get("Table")).toLowerCase());
					// 创建文件
					GeneratorUtils.writer(template, modelMap, path + "/" + fileNameNew);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}

	/***
	 * 首字母大写
	 * 
	 * @param str
	 * @return
	 */
	public static String firstUpper(String str) {
		// log.info("str:"+str+",str.length="+str.length());
		if (!org.springframework.util.StringUtils.isEmpty(str)) {
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		} else {
			return str;
		}
	}

	/**
	 * 首字母小写
	 * 
	 * @param str
	 * @return
	 */
	public static String firstLower(String str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

	/***
	 * 移除tab_,tb_
	 * 
	 * @return
	 */
	public static String replaceTab(String str) {
		return str.replaceFirst("tab_", "").replaceFirst("tb_", "");
	}

	/***
	 * 将下划线替换掉
	 * 
	 * @param str
	 * @return
	 */
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

	/***
	 * 根据java.sql.Types的值返回java的类型
	 * 
	 * @param value
	 * @return
	 */
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
	 * 去掉数据类型的包
	 * 
	 * @param type
	 * @return
	 */
	public static String simpleName(String type) {
		return type.replace("java.lang.", "").replaceFirst("java.util.", "");
	}

	/***
	 * 去掉数据类型的包，并且首字母小写
	 * 
	 * @param type
	 * @return
	 */
	public static String simpleNameLowerFirst(String type) {
		// 去掉前缀
		type = simpleName(type);
		// 将第一个字母转成小写
		return GeneratorUtils.firstLower(type);
	}

	public static String upperCaseFirstWord(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

//	public static void main(String[] args) {
//        // This is the path where the file's name you want to take.
//        String path = "D:\\workspace\\github\\jun_code_generator\\jun_code_helper\\code_generator\\src\\main\\resources\\template_ds";
//        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//        getFile(path,list);
//    }

	public static Boolean skipTables(String str) {
		str = str.toLowerCase();
		for (String x : GeneratorUtils.SKIPTABLE.split(",")) {
			if (str.contains(x.toLowerCase())) {
				return true;
			}
		}
		return true;
	}

	public static Boolean includeTabbles(String str) {
		str = str.toLowerCase();
		if (GeneratorUtils.INCLUETABLES.equals("*")) {
			return false;
		}
		for (String x : GeneratorUtils.INCLUETABLES.split(",")) {
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

	public static String replaceRow(String str) {
		str = str.toLowerCase().replaceFirst("tab_", "").replaceFirst("tb_", "").replaceFirst("t_", "");
		for (String x : GeneratorUtils.ROWREMOVEPREFIXES.split(",")) {
			str = str.replaceFirst(x.toLowerCase(), "");
		}
		return str;
	}
}
