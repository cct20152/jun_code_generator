package com.jun.plugin.codegenerator.admin.test.generator;

import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import com.jun.plugin.codegenerator.admin.test.configuration.ConstantProperties;
import com.jun.plugin.codegenerator.admin.test.domain.TableProperties;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class ApiGenerator {

    public void generatorApi(Configuration cfg, TableProperties tableProperties) {
        String modelNameUpperCamel = tableProperties.getModelNameUpperCamel();
        log.info(">>>>>>>>>>>>>生成Api：{}开始>>>>>>>>>>>>>>>>>>", modelNameUpperCamel);
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("projectName",ConstantProperties.PROJECT_NAME);
            data.put("date", ConstantProperties.NOW_DATE);
            data.put("author", tableProperties.getAuthor());
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", tableProperties.getModelNameLowerCamel());
            data.put("basePackage", ConstantProperties.BASE_PACKAGE);
            String pathname = ConstantProperties.API_JAVA_PATH + ConstantProperties.PACKAGE_PATH_API + modelNameUpperCamel + "Api.java";
            File file = new File(pathname);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("api.ftl").process(data, new FileWriter(file));
            log.info(">>>>>>>>>>>>>生成Api：{}结束>>>>>>>>>>>>>>>>>>", modelNameUpperCamel);
        } catch (Exception e) {
            log.info(">>>>>>>>>>>>>生成Api：{}失败>>>>>>>>>>>>>>>>>>", modelNameUpperCamel);
            throw new RuntimeException("生成Api失败", e);
        }
    }
}
