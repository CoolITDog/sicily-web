package zy.com.cn.demo.take.task.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import zy.com.cn.demo.take.task.mapper.UserInfoMapper;
import zy.com.cn.demo.take.task.model.UserInfo;
import zy.com.cn.demo.take.task.service.UserInfoService;

import java.util.List;

/**
 * @title: UserInfoServiceImpl
 * @description: 用户信息服务实现
 * @author: zhangyan
 * @date: 2020-03-12 13:18
 * @version: 1.0
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public UserInfo insertUserInfo(UserInfo userInfo) {
        Assert.notNull(userInfo, "userInfo is null");
        try{
            userInfoMapper.insertRecord(userInfo);
            return userInfo;
        }catch (Exception e){
            logger.error("新增用户失败：{}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<UserInfo> listUsers(UserInfo userInfo) {
        Assert.notNull(userInfo, "userInfo is null");
        try{
            return userInfoMapper.listRecord(userInfo);
        }catch (Exception e){
            logger.error("查询用户列表失败：{}", e.getMessage(),e);
            return null;
        }
    }
}
