package com.jun.plugin;

import java.net.Inet4Address;
import java.net.InetAddress;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableScheduling
@SpringBootApplication // 多数据源 (exclude = DruidDataSourceAutoConfigure.class)
@MapperScan({"com.jun.plugin.**.mapper","com.jun.plugin.**.dao"})
public class SpringbootApplication extends SpringBootServletInitializer {

	public static void main(String[] args) throws Exception {
//      SpringApplication application = new SpringApplication(SpringbootApplication.class);
//		application.setBannerMode(Banner.Mode.OFF);
//		application.run(args);
		ConfigurableApplicationContext application = SpringApplication.run(SpringbootApplication.class, args);
		Environment env = application.getEnvironment();
		InetAddress inetAddress = Inet4Address.getLocalHost();
		String hostAddress = inetAddress.getHostAddress();
		String serverPort = env.getProperty("server.port");
		String serverPath = env.getProperty("spring.application.name");
		log.info("项目启动成功！访问地址: http://{}:{}/{}", hostAddress, serverPort, serverPath);
		log.info("本机地址: http://localhost:{}", serverPort);
	}

	@Override // 为了打包springboot项目
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(this.getClass());
	}
}
