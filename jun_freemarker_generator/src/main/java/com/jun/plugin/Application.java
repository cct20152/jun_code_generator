package com.jun.plugin;

//import org.mybatis.spring.annotation.MapperScan;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.jun.plugin")
//@ComponentScan(basePackages = "com.jun.plugin")
//@ComponentScan(basePackages = "com.jun.plugin")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
