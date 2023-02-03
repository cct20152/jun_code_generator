package com.jun.plugin.utils;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.jun.plugin.entity.ColumnEntity;
import com.jun.plugin.entity.TableEntity;

import cn.hutool.core.date.DateTime;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器   工具类
 *
 * @author hermit
 * @email chenqiang_cool@126.com
 * @date 2016年12月19日 下午11:40:24
 */
public class GenUtils {
	
	public static List<String> getTemplates(){
		return getTemplates2();
	}

    public static List<String> getTemplates1(){
        List<String> templates = new ArrayList<String>();
        templates.add("template/Entity.java.vm");
        templates.add("template/Dao.java.vm");
        templates.add("template/Dao.xml.vm");
        templates.add("template/Service.java.vm");
        templates.add("template/ServiceImpl.java.vm");
        templates.add("template/Controller.java.vm");
        templates.add("template/list.html.vm");
        templates.add("template/add.html.vm");
        templates.add("template/edit.html.vm");
        return templates;
    }
    
    public static List<String> getTemplates2() {
        List<String> templates = new ArrayList<>();
        templates.add("vm/Entity.java.vm");
        templates.add("vm/Dao.java.vm");
        templates.add("vm/Dao.xml.vm");
        templates.add("vm/Service.java.vm");
        templates.add("vm/ServiceImpl.java.vm");
        templates.add("vm/Controller.java.vm");
        templates.add("vm/menu.sql.vm");
        templates.add("vm/list.html.vm");
        return templates;
    }
    
    public static List<String> getTemplates3() {
        List<String> templates = new ArrayList<String>();

        //java代码模板
        templates.add("auto_code/model/Entity.java.vm");
        //templates.add("auto_code/model/EntityExample.java.vm");
        templates.add("auto_code/mapperxml/EntityMapper.xml.vm");
        templates.add("auto_code/service/EntityService.java.vm");
        templates.add("auto_code/service/impl/EntityServiceImpl.java.vm");
        templates.add("auto_code/mapper/EntityMapper.java.vm");
        templates.add("auto_code/controller/EntityController.java.vm");
        //前端模板
        templates.add("auto_code/html/list.html.vm");
        templates.add("auto_code/html/add.html.vm");
        templates.add("auto_code/html/edit.html.vm");
        //sql模板
        templates.add("auto_code/sql/menu.sql.vm");
        //templates.add("auto_code/说明.txt.vm");
        return templates;
    }

    /**
     * 生成代码
     */
    public static void generatorCode(Map<String, String> table,
                                     List<Map<String, String>> columns, ZipOutputStream zip) {
        //配置信息
        Configuration config = getConfig();
        boolean hasBigDecimal = false;
        boolean hasDate = false;
        //表信息
        TableEntity tableEntity = new TableEntity();
        tableEntity.setTableName(table.get("tableName" ));
        tableEntity.setComments(table.get("tableComment" ));
        //表名转换成Java类名
        String className = tableToJava(tableEntity.getTableName(), config.getString("tablePrefix" ));
        tableEntity.setClassName(className);
        tableEntity.setClassname(StringUtils.uncapitalize(className));

        //列信息
        List<ColumnEntity> columsList = new ArrayList<>();
        for(Map<String, String> column : columns){
            ColumnEntity columnEntity = new ColumnEntity();
            columnEntity.setColumnName(column.get("columnName" ));
            columnEntity.setDataType(column.get("dataType" ));
            columnEntity.setComments(column.get("columnComment" ));
            columnEntity.setExtra(column.get("extra" ));
            columnEntity.setMaxLength(String.valueOf(column.get("maxLength")));
            columnEntity.setIsNull(column.get("isNull"));

            //列名转换成Java属性名
            String attrName = columnToJava(columnEntity.getColumnName());
            columnEntity.setAttrName(attrName);
            columnEntity.setAttrname(StringUtils.uncapitalize(attrName));

            //列的数据类型，转换成Java类型
            String attrType = config.getString(columnEntity.getDataType(), "unknowType" );
            columnEntity.setAttrType(attrType);
            if (!hasBigDecimal && attrType.equals("BigDecimal" )) {
                hasBigDecimal = true;
            }
            if (!hasDate && attrType.equals("Date" )) {
                hasDate = true;
            }
            //是否主键
            if ("PRI".equalsIgnoreCase(column.get("columnKey" )) && tableEntity.getPk() == null) {
                tableEntity.setPk(columnEntity);
            }

            columsList.add(columnEntity);
        }
        tableEntity.setColumns(columsList);

        //没主键，则第一个字段为主键
        if (tableEntity.getPk() == null) {
            tableEntity.setPk(tableEntity.getColumns().get(0));
        }

        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader" );
        Velocity.init(prop);
        String mainPath = config.getString("mainPath" );
        mainPath = StringUtils.isBlank(mainPath) ? "com.jun.plugin" : mainPath;
        //封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", tableEntity.getTableName());
        //去除表字
        if (StringUtils.isNotBlank(tableEntity.getComments())) {
            if(StringUtils.endsWith(tableEntity.getComments(),"表")){
                tableEntity.setComments(StringUtils.substring(tableEntity.getComments(), 0, tableEntity.getComments().length()-1));
            }
        }
        map.put("comments", tableEntity.getComments());
        map.put("pk", tableEntity.getPk());
        map.put("className", tableEntity.getClassName());
        map.put("classname", tableEntity.getClassname());
        map.put("pathName", tableEntity.getClassname().toLowerCase());
        map.put("columns", tableEntity.getColumns());
        map.put("hasBigDecimal", hasBigDecimal);
        map.put("hasDate", hasDate);
        map.put("mainPath", mainPath);
//        map.put("package", config.getString("package"));
//        map.put("author", config.getString("author"));
//        map.put("email", config.getString("email"));
        map.put("package", table.get("packageName" ));
        map.put("moduleName", table.get("moduleName" ));
        map.put("author",table.get("author" ));
        map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
        map.put("identity", IdWorker.getId());
        map.put("addId", IdWorker.getId());
        map.put("updateId", IdWorker.getId());
        map.put("deleteId", IdWorker.getId());
        map.put("selectId", IdWorker.getId());
        map.put("classNameLower", tableEntity.getClassNameLower());
        VelocityContext context = new VelocityContext(map);
        
        

        //获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8" );
            tpl.merge(context, sw);

            try {
                //添加到zip
//            	zip.putNextEntry(new ZipEntry(Objects.requireNonNull(getFileName(template, tableEntity.getClassName(), config.getString("package")))));
                zip.putNextEntry(new ZipEntry(getFileName(template, tableEntity.getClassName(), table.get("packageName" ), table.get("moduleName" ))));
                IOUtils.write(sw.toString(), zip, "UTF-8" );
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                throw new RRException("渲染模板失败，表名：" + tableEntity.getTableName(), e);
            }
        }
    }


    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "" );
    }
    
    /**
     * 列名转换成Java属性名
     */
    @Deprecated
    public static String columnToJava_old(String field) {
        String[] fields = field.split("_");
        StringBuilder sbuilder = new StringBuilder(fields[0]);
        for (int i = 1; i < fields.length; i++) {
            char[] cs = fields[i].toCharArray();
            cs[0] -= 32;
            sbuilder.append(String.valueOf(cs));
        }
        return sbuilder.toString().substring(0, 1).toUpperCase() + sbuilder.toString().substring(1);
    }

    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String tablePrefix) {
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replace(tablePrefix, "" );
        }
        return columnToJava(tableName);
    }
    
    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String[] tablePrefixArray) {
        tableName = tableName.toLowerCase();
        if (null != tablePrefixArray && tablePrefixArray.length > 0) {
            for (String tablePrefix : tablePrefixArray) {
                tablePrefix = tablePrefix.toLowerCase();
                tableName = tableName.replace(tablePrefix, "");
            }
        }
        return columnToJava(tableName);
    }

    /**
     * 获取配置信息
     */
    public static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties" );
        } catch (ConfigurationException e) {
            throw new RRException("获取配置文件失败，", e);
        }
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, String className, String packageName, String moduleName) {
        String packagePath = "main" + File.separator + "java" + File.separator;
        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator + moduleName + File.separator;
        }

        if (template.contains("Entity.java.vm" )) {
        	return packagePath + "entity" + File.separator + className + "Entity.java";
            //return packagePath + "entity" + File.separator + className + ".java";
        }

        if (template.contains("Dao.java.vm" )) {
            return packagePath + "dao" + File.separator + className + "Mapper.java";
        }

        if (template.contains("Service.java.vm" )) {
            return packagePath + "service" + File.separator + "I" + className + "Service.java";
        }

        if (template.contains("ServiceImpl.java.vm" )) {
            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if (template.contains("Controller.java.vm" )) {
        	return packagePath + "controller" + File.separator + className + "Controller.java";
            //return packagePath + "ctrl" + File.separator + className + "Ctrl.java";
        }
        

        if (template.contains("Dao.xml.vm" )) {
        	return "main" + File.separator + "resources" + File.separator + "mapper" + File.separator + className + "Mapper.xml";
            //return "main" + File.separator + "resources" + File.separator + "mapper" + File.separator + moduleName + File.separator + className + "Mapper.xml";
        }

        if (template.contains("list.html.vm" )) {
        	//return "main" + File.separator + "resources" + File.separator + "templates2" + File.separator + className.toLowerCase() + File.separator + "list" + ".html";
            return "main" + File.separator + "resources" + File.separator + "static" + File.separator + "views" + File.separator
                    + moduleName + File.separator + className.toLowerCase() + ".html";
        }
        if (template.contains("add.html.vm" )) {
            return "main" + File.separator + "resources" + File.separator + "static" + File.separator + "views" + File.separator
                    + moduleName + File.separator + "template" + File.separator + className.toLowerCase()+ "_add.html";
        }
        if (template.contains("edit.html.vm" )) {
            return "main" + File.separator + "resources" + File.separator + "static" + File.separator + "views" + File.separator
                    + moduleName + File.separator + "template" + File.separator+ className.toLowerCase()+ "_edit.html";
        }

        if (template.contains("menu.sql.vm" )) {
            return className.toLowerCase() + "_menu.sql";
        }

        return null;
    }
    
    
    /**
     * 预览方法
     *
     * @param tableInfo 数据库表
     * @return
     * @author fuce
     * @Date 2021年1月18日 上午2:10:55
     */
	public static Map<String, String> viewAuto(/* TableInfo tableInfo, AutoConfigModel autoConfigModel */) {
        Map<String, String> velocityMap = new HashMap<String, String>();

        //AutoCodeConfig autoCodeConfig = new AutoCodeConfig();
        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class" , "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
        Map<String, Object> map = new HashMap<>();
        //数据库表数据
//        map.put("tableInfo" , tableInfo);
        //字段集合
//        map.put("beanColumns" , tableInfo.getBeanColumns());
        //配置文件
        map.put("SnowflakeIdWorker" , SnowflakeIdWorker.class);
        //class类路径
        //map.put("parentPack" , autoCodeConfig.getConfigkey("parentPack"));
        //作者
//        map.put("author" , autoConfigModel.getAuthor());
        //时间
        map.put("datetime" , new DateTime());
        //sql需要的权限父级pid
//        map.put("pid" , autoConfigModel.getPid());

        VelocityContext velocityContext = new VelocityContext(map);
        //获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            StringWriter sw = new StringWriter();
            tpl.merge(velocityContext, sw);
            System.out.println("输出模板");
            System.out.println(sw);
            System.out.println("输出模板 end");
            velocityMap.put(template.substring(template.lastIndexOf("/") + 1, template.lastIndexOf(".vm")), sw.toString());
        }
        return velocityMap;
    }
}
