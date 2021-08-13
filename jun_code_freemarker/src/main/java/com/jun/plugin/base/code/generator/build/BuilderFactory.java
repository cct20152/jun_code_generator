package com.jun.plugin.base.code.generator.build;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/****
 * @Author:shenkunlin
 * @Description:构建对象的工厂
 * @Date 2019/6/14 23:21
 *****/
public class BuilderFactory {

    /***
     * 构建Controller
     * @param modelMap
     */
    public static void builder(Map<String,Object> modelMap,//数据模型
                                String templatePath, //模板路径
                                String templateFile, //模板文件
                                String storePath,    //存储路径
                                String suffix){      //生成文件后缀名字
        try {
            //获取模板对象
            Template template = BuilderFactory.loadTemplate(ControllerBuilder.class.getResource(templatePath).getPath(), templateFile);

            //创建文件夹
            String path = TemplateBuilder.PROJECT_PATH+storePath.replace(".","/");
            File file = new File(path);
            if(!file.exists()){
                file.mkdirs();
            }

            //创建文件
            BuilderFactory.writer(template,modelMap,path+"/"+modelMap.get("Table")+suffix);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    /***
     * 创建模板对象
     * @param path
     * @param ftl
     * @return
     * @throws Exception
     */
    public static Template loadTemplate(String path, String ftl) throws Exception{
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
    public static void writer(Template template,Map dataModel,String file) throws Exception{
        //包参数
        dataModel.put("package_controller",TemplateBuilder.PACKAGE_CONTROLLER);
        dataModel.put("package_pojo",TemplateBuilder.PACKAGE_POJO);
        dataModel.put("package_mapper",TemplateBuilder.PACKAGE_MAPPER);
        dataModel.put("package_service",TemplateBuilder.PACKAGE_SERVICE_INTERFACE);
        dataModel.put("package_service_impl",TemplateBuilder.PACKAGE_SERVICE_INTERFACE_IMPL);

        // 创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名。
        Writer out = new FileWriter(new File(file));
        // 调用模板对象的process方法输出文件。
        template.process(dataModel, out);
        // 关闭流。
        out.close();
    }

}
