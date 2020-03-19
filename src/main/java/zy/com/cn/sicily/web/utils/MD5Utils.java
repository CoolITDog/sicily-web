package zy.com.cn.sicily.web.utils;

import org.springframework.util.DigestUtils;

/**
 * @title: MD5Utils
 * @description: MD5加密
 * @author: zhangyan
 * @date: 2020-03-19 11:06
 * @version: 1.0
 **/
public class MD5Utils {

    public static void MD5Code(String plainText){
        String password = DigestUtils.md5DigestAsHex(plainText.getBytes());
        System.out.println(password);
    }
}
