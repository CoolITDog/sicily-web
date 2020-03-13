package zy.com.cn.demo.take.task;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "zy.com.cn.demo.take.task.*")
public class DemoTakeTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoTakeTaskApplication.class, args);
    }

}
