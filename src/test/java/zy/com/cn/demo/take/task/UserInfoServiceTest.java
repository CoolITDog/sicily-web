package zy.com.cn.demo.take.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import zy.com.cn.demo.take.task.model.UserInfo;
import zy.com.cn.demo.take.task.service.UserInfoService;

import java.util.List;

/**
 * @title: UserInfoServiceTest
 * @description: 用户信息接口测试用例
 * @author: zhangyan
 * @date: 2020-03-12 13:21
 * @version: 1.0
 **/
public class UserInfoServiceTest extends DemoTakeTaskApplicationTests {
    @Autowired
    private UserInfoService userInfoService;
    @Test
    public void insertUserInfoTest(){
        UserInfo info = new UserInfo();
        info.setWechatName("Sisi");
        UserInfo res = userInfoService.insertUserInfo(info);
        System.out.println(res);
    }

    @Test
    public void listUsersTest(){
        UserInfo info = new UserInfo();
        List<UserInfo> list = userInfoService.listUsers(info);
        System.out.println(list);
    }
}
