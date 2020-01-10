package zy.com.cn.demo.take.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zy.com.cn.demo.take.task.mapper.UserMapper;
import zy.com.cn.demo.take.task.model.User;

/**
 * @title: UserService
 * @description: 用户信息服务层
 * @author: zhangyan
 * @date: 2020-01-07 16:48
 * @version: 1.0
 **/
public interface UserService {

    int addUser(User user);
}
