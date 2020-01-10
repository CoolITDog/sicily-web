package zy.com.cn.demo.take.task;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("zy.com.cn.demo.take.task.mapper")
public class DemoTakeTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoTakeTaskApplication.class, args);
    }

}
