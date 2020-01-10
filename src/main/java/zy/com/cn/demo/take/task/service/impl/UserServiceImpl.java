package zy.com.cn.demo.take.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zy.com.cn.demo.take.task.mapper.UserMapper;
import zy.com.cn.demo.take.task.model.User;
import zy.com.cn.demo.take.task.service.UserService;

/**
 * @title: UserServiceImpl
 * @description: 用户服务逻辑实现层
 * @author: zhangyan
 * @date: 2020-01-07 17:18
 * @version: 1.0
 **/
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public int addUser(User user){
        return  userMapper.insert(user);
    }
}
