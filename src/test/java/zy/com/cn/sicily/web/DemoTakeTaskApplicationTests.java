package zy.com.cn.sicily.web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import zy.com.cn.sicily.web.utils.RedisUtil;

import javax.annotation.Resource;

@SpringBootTest
class DemoTakeTaskApplicationTests {

    @Resource
    private RedisUtil redisUtil;
    @Test
    void contextLoads() {
    }

    /**
     * 插入缓存数据
     */
    @Test
    public void set() {
        redisUtil.set("redis_key_2", "redis_vale");
    }

    /**
     * 读取缓存数据
     */
    @Test
    public void get() {
        String value = (String) redisUtil.get("redis_key_2");
        System.out.println(value);
    }

}
