package com.jun.plugin.codegenerator.admin.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 通用配置
 * 
 */
@Configuration
public class WebMvcConfigurerAddCorsMappings implements WebMvcConfigurer {
	
	/**
	 * 首页地址
	 */
//    @Value("${shiro.user.indexUrl}")
//    private String indexUrl;

//    @Autowired
//    private RepeatSubmitInterceptor repeatSubmitInterceptor;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		WebMvcConfigurer.super.addCorsMappings(registry);
		registry.addMapping("/**").allowedHeaders("*").allowedMethods("POST", "GET", "PUT", "DELETE")
				.allowedOrigins("*");
	}

	/**
	 * 默认首页的设置，当输入域名是可以自动跳转到默认指定的网页
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/").setViewName("forward:" + indexUrl);
		registry.addViewController("/").setViewName("forward:" + "login.html");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		/** 本地文件上传路径 */
//        registry.addResourceHandler(Constants.RESOURCE_PREFIX + "/**").addResourceLocations("file:" + Global.getProfile() + "/");

		registry.addResourceHandler("/**").addResourceLocations("classpath:/templates/","classpath:/templates2/","classpath:/views/");
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//		registry.addResourceHandler("/**").addResourceLocations("classpath:/templates/");
		registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

		/** swagger配置 */
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		/** 文件下载映射配置,同下 */
        //registry.addResourceHandler(fileUploadProperties.getAccessUrl()).addResourceLocations("file:" + fileUploadProperties.getPath());
	}


	/**
	 * 自定义拦截规则
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(repeatSubmitInterceptor).addPathPatterns("/**");
	}
}