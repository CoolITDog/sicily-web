package zy.com.cn.sicily.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = {"zy.com.cn.sicily.web.*"})
@EnableTransactionManagement //启用注解事务器
public class DemoTakeTaskApplication extends SpringBootServletInitializer {
    // 继承SpringBootServletInitializer，配置MVC
    public static void main(String[] args) {
        SpringApplication.run(DemoTakeTaskApplication.class, args);
    }

    // 重写SpringBootServletInitializer的configure方法
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(DemoTakeTaskApplication.class);
    }
}
