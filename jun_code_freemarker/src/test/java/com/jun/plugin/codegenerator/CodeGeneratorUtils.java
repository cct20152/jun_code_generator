package com.jun.plugin.codegenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jun.plugin.codegenerator.admin.core.model.ClassInfo;
import com.jun.plugin.codegenerator.admin.core.model.FieldInfo;

import cn.hutool.core.util.ArrayUtil;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * 代码生成器，根据DatabaseMetaData及数据表名称生成对应的Model、Mapper、Service、Controller简化基础代码逻辑开发。
 * 
 * @author Wujun
 */
public class CodeGeneratorUtils {
	private static final Logger logger = LoggerFactory.getLogger(CodeGeneratorUtils.class);
	
	public static void main(String[] args) throws Exception {
		String tables = "app_envt,app_member,app_datasource,app_git_config,git_branch,git_user,app_deploy_config";
		genTables(tables.split(","));
	}


	public static void genTables(String [] tables) throws Exception {
		List<ClassInfo> classInfos = CodeGeneratorUtils.getClassInfo(tables);
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
				//processTemplatesStringWriter(datas, result);
				processTemplates(classInfo, datas);
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


	public static void processTemplates(ClassInfo classInfo, Map<String, Object> datas) throws IOException, TemplateException {
		List<String> templates = GenUtils.getTemplates();
		for(int i = 0 ; i < templates.size() ; i++) {
			CodeGeneratorUtils.processFile(templates.get(i), datas, GenUtils.getFilePaths(classInfo).get(i));
		}
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

	/***
	 * 模板构建，StringWriter 返回构建后的文本，不生成文件
	 */
	public static String processString(String templateName, Map<String, Object> params)
			throws IOException, TemplateException {
		Template template = getConfiguration().getTemplate(templateName);
		StringWriter result = new StringWriter();
		template.process(params, result);
		String htmlText = result.toString();
		return htmlText;
	}

	private static freemarker.template.Configuration getConfiguration() throws IOException {
		freemarker.template.Configuration cfg = new freemarker.template.Configuration(
				freemarker.template.Configuration.VERSION_2_3_23);
		cfg.setDirectoryForTemplateLoading(new File(GenUtils.TEMPLATE_FILE_PATH));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		return cfg;
	}


	/***
	 * 模板构建，输出源码字符串
	 */
	public static void processTemplatesStringWriter(Map<String, Object> datas, Map<String, String> result)
			throws IOException, TemplateException {
		result.put("controller_code", CodeGeneratorUtils.processString("code-generator/controller.ftl", datas));
		result.put("service_code", CodeGeneratorUtils.processString("code-generator/service.ftl", datas));
		result.put("service_impl_code", CodeGeneratorUtils.processString("code-generator/service_impl.ftl", datas));
		result.put("dao_code", CodeGeneratorUtils.processString("code-generator/dao.ftl", datas));
		result.put("mybatis_code", CodeGeneratorUtils.processString("code-generator/mybatis.ftl", datas));
		result.put("model_code", CodeGeneratorUtils.processString("code-generator/model.ftl", datas));
		System.out.println(result);
	}
	
	public static List<ClassInfo> getClassInfo(String[] tables) {
		List<ClassInfo> list = new ArrayList<ClassInfo>();
		try {
			Connection conn = DriverManager.getConnection(GenUtils.props.getProperty("url"), GenUtils.props.getProperty("uname"),
					GenUtils.props.getProperty("pwd"));
			DatabaseMetaData metaData = conn.getMetaData();
			String databaseType = metaData.getDatabaseProductName(); // 获取数据库类型：MySQL
			// 针对MySQL数据库进行相关生成操作
			if (databaseType.equals("MySQL")) {
				ResultSet tableResultSet = metaData.getTables(conn.getCatalog(), conn.getSchema() /*"%"*/,  "%", new String[] { "TABLE" }); // 获取所有表结构
				String database = conn.getCatalog(); // 获取数据库名字
				while (tableResultSet.next()) { // 循环所有表信息
					String tableName = tableResultSet.getString("TABLE_NAME"); // 获取表名
					if( tables == null  || ArrayUtil.containsIgnoreCase(tables, tableName)) {
						List<Map<String,String>> pkList = getPrimaryKeysInfo(metaData,tableName);
						String table = GenUtils.replace_(GenUtils.replaceTabblePreStr(tableName)); // 名字操作,去掉tab_,tb_，去掉_并转驼峰
						String Table = GenUtils.firstUpper(table); // 获取表名,首字母大写
						String tableComment = tableResultSet.getString("REMARKS"); // 获取表备注
						String className = GenUtils.replace_(GenUtils.replaceTabblePreStr(tableName)); // 名字操作,去掉tab_,tb_，去掉_并转驼峰
						String classNameFirstUpper = GenUtils.firstUpper(className); // 大写对象
//						showTableInfo(tableResultSet); 
						logger.info("当前表名：" + tableName);
						Set<String> typeSet = new HashSet<String>(); // 所有需要导包的类型
						ResultSet cloumnsSet = metaData.getColumns(database, GenUtils.props.getProperty("uname"), tableName, null); // 获取表所有的列
						ResultSet keySet = metaData.getPrimaryKeys(database, GenUtils.props.getProperty("uname"), tableName); // 获取主键
						String key = "", keyType = "";
						while (keySet.next()) {
							key = keySet.getString(4);
						}
						// V1 初始化数据及对象 模板V1 field List
						List<FieldInfo> fieldList = new ArrayList<FieldInfo>();
						while (cloumnsSet.next()) {
							String remarks = cloumnsSet.getString("REMARKS");// 列的描述
							String columnName = cloumnsSet.getString("COLUMN_NAME"); // 获取列名
							String javaType = GenUtils.getType(cloumnsSet.getInt("DATA_TYPE"));// 获取类型，并转成JavaType
							int COLUMN_SIZE = cloumnsSet.getInt("COLUMN_SIZE");// 获取
							String TABLE_SCHEM = cloumnsSet.getString("TABLE_SCHEM");// 获取
							String COLUMN_DEF = cloumnsSet.getString("COLUMN_DEF");// 获取
							int NULLABLE = cloumnsSet.getInt("NULLABLE");// 获取
							int DATA_TYPE = cloumnsSet.getInt("DATA_TYPE");// 获取
							// showColumnInfo(cloumnsSet);
							String propertyName = GenUtils.replace_(GenUtils.replaceRow(columnName));// 处理列名，驼峰
							typeSet.add(javaType);// 需要导包的类型
							Boolean isPk = false;
							if (columnName.equals(key)) {
								keyType = GenUtils.simpleName(javaType);// 主键类型,单主键支持
								isPk = true;
							}
							// V1 初始化数据及对象
							FieldInfo fieldInfo = new FieldInfo();
							fieldInfo.setColumnName(columnName);
							fieldInfo.setFieldName(propertyName);
							fieldInfo.setFieldClass(GenUtils.simpleName(javaType));
							fieldInfo.setFieldComment(remarks);
							fieldInfo.setColumnSize(COLUMN_SIZE);
							fieldInfo.setNullable(NULLABLE==0);
							fieldInfo.setFieldType(javaType);
							fieldInfo.setColumnType(javaType);
							fieldInfo.setIsPrimaryKey(isPk);
							fieldList.add(fieldInfo);
						}
						// ************************************************************************
						if (fieldList != null && fieldList.size() > 0) {
							ClassInfo classInfo = new ClassInfo();
							classInfo.setTableName(tableName);
							classInfo.setClassName(classNameFirstUpper);
							classInfo.setClassComment(tableComment);
							classInfo.setFieldList(fieldList);
							classInfo.setPkSize(pkList.size());
							list.add(classInfo);
						}
						// ************************************************************************
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	//获取表主键信息
	public static List getPrimaryKeysInfo(DatabaseMetaData dbmd,String tablename) {
		List pkList = Lists.newArrayList();
	    ResultSet rs = null;
	    try {
	        rs = dbmd.getPrimaryKeys(null, null, tablename);
	        while (rs.next()) {
	            String tableCat = rs.getString("TABLE_CAT");  //表类别(可为null)
	            String tableSchemaName = rs.getString("TABLE_SCHEM");//表模式（可能为空）,在oracle中获取的是命名空间,其它数据库未知
	            String tableName = rs.getString("TABLE_NAME");  //表名
	            String columnName = rs.getString("COLUMN_NAME");//列名
	            short keySeq = rs.getShort("KEY_SEQ");//序列号(主键内值1表示第一列的主键，值2代表主键内的第二列)
	            String pkName = rs.getString("PK_NAME"); //主键名称
	            Map m = Maps.newHashMap();
	            m.put("COLUMN_NAME", columnName);
	            m.put("KEY_SEQ", keySeq);
	            m.put("PK_NAME", pkName);
	            pkList.add(m);
	            System.out.println(tableCat + " - " + tableSchemaName + " - " + tableName + " - " + columnName + " - " + keySeq + " - " + pkName);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return pkList;
	}

	

//	/***
//	 * 数据构建 ClassInfo
//	 */
//	public static List<ClassInfo> getClassInfoNew(String[] tables) {
//		List<ClassInfo> list = new ArrayList<ClassInfo>();
//		try {
//			DruidDataSource dataSource = new DruidDataSource();
//			dataSource.setUrl(GenUtils.props.getProperty("url"));
//			dataSource.setUsername(GenUtils.props.getProperty("uname"));
//			dataSource.setPassword(GenUtils.props.getProperty("pwd"));
//			Connection conn = dataSource.getConnection();
//			DatabaseMetaData metaData = conn.getMetaData();
//			String databaseType = metaData.getDatabaseProductName(); // 获取数据库类型：MySQL
//			List<String> tableNames = MetaUtil.getTables(dataSource,"lcp-dev",TableType.TABLE);
//			// 针对MySQL数据库进行相关生成操作
//			if (databaseType.equals("MySQL")) {
//				ResultSet tableResultSet = metaData.getTables(null, "%", "%", new String[] { "TABLE" }); // 获取所有表结构
//				String database = conn.getCatalog(); // 获取数据库名字
//
//				while (tableResultSet.next()) { // 循环所有表信息
//					String tableName = tableResultSet.getString("TABLE_NAME"); // 获取表名
//					if( tables == null  || ArrayUtil.containsIgnoreCase(tables, tableName)) {
//						Table tab = MetaUtil.getTableMeta(dataSource, databaseType);
//						
//						String table = CodeGeneratorUtils.replace_(CodeGeneratorUtils.replaceTabblePreStr(tableName)); // 名字操作,去掉tab_,tb_，去掉_并转驼峰
//						String Table = CodeGeneratorUtils.firstUpper(table); // 获取表名,首字母大写
//						String tableComment = tableResultSet.getString("REMARKS"); // 获取表备注
//						String className = CodeGeneratorUtils.replace_(CodeGeneratorUtils.replaceTabblePreStr(tableName)); // 名字操作,去掉tab_,tb_，去掉_并转驼峰
//						String classNameFirstUpper = CodeGeneratorUtils.firstUpper(className); // 大写对象
////						showTableInfo(tableResultSet); 
//						logger.info("当前表名：" + tableName);
//						
//						Set<String> typeSet = new HashSet<String>(); // 所有需要导包的类型
//						ResultSet cloumnsSet = metaData.getColumns(database, GenUtils.props.getProperty("uname"), tableName, null); // 获取表所有的列
//						ResultSet keySet = metaData.getPrimaryKeys(database, GenUtils.props.getProperty("uname"), tableName); // 获取主键
//						String key = "", keyType = "";
//						while (keySet.next()) {
//							key = keySet.getString(4);
//						}
//						// V1 初始化数据及对象 模板V1 field List
//						List<FieldInfo> fieldList = new ArrayList<FieldInfo>();
//						
//						while (cloumnsSet.next()) {
//							String remarks = cloumnsSet.getString("REMARKS");// 列的描述
//							String columnName = cloumnsSet.getString("COLUMN_NAME"); // 获取列名
//							String javaType = CodeGeneratorUtils.getType(cloumnsSet.getInt("DATA_TYPE"));// 获取类型，并转成JavaType
//							int COLUMN_SIZE = cloumnsSet.getInt("COLUMN_SIZE");// 获取
//							String TABLE_SCHEM = cloumnsSet.getString("TABLE_SCHEM");// 获取
//							String COLUMN_DEF = cloumnsSet.getString("COLUMN_DEF");// 获取
//							int NULLABLE = cloumnsSet.getInt("NULLABLE");// 获取
//							// showColumnInfo(cloumnsSet);
//							String propertyName = CodeGeneratorUtils.replace_(CodeGeneratorUtils.replaceRow(columnName));// 处理列名，驼峰
//							typeSet.add(javaType);// 需要导包的类型
//							if (columnName.equals(key)) {
//								keyType = CodeGeneratorUtils.simpleName(javaType);// 主键类型,单主键支持
//							}
//							// V1 初始化数据及对象
//							FieldInfo fieldInfo = new FieldInfo();
//							fieldInfo.setColumnName(columnName);
//							fieldInfo.setFieldName(propertyName);
//							fieldInfo.setFieldClass(CodeGeneratorUtils.simpleName(javaType));
//							fieldInfo.setFieldComment(remarks);
//							fieldList.add(fieldInfo);
//						}
//						// ************************************************************************
//						if (fieldList != null && fieldList.size() > 0) {
//							ClassInfo classInfo = new ClassInfo();
//							classInfo.setTableName(tableName);
//							classInfo.setClassName(classNameFirstUpper);
//							classInfo.setClassComment(tableComment);
//							classInfo.setFieldList(fieldList);
//							list.add(classInfo);
//						}
//						// ************************************************************************
//					}
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}

}
