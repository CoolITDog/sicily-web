package zy.com.cn.sicily.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"zy.com.cn.sicily.web.*"})
public class DemoTakeTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoTakeTaskApplication.class, args);
    }
}
