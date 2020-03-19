package zy.com.cn.sicily.web;

import org.junit.jupiter.api.Test;
import zy.com.cn.sicily.web.utils.MD5Utils;

/**
 * @title: MD5UtilsTest
 * @description: MD5加密测试
 * @author: zhangyan
 * @date: 2020-03-19 11:10
 * @version: 1.0
 **/
public class MD5UtilsTest extends DemoTakeTaskApplicationTests {

    @Test
    public void test(){
        MD5Utils.MD5Code("123123");
    }
}
