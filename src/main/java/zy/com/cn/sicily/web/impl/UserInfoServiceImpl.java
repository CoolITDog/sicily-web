package zy.com.cn.sicily.web.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import zy.com.cn.sicily.web.mapper.UserInfoMapper;
import zy.com.cn.sicily.web.model.UserInfo;
import zy.com.cn.sicily.web.service.UserInfoService;

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
            userInfoMapper.insertUser(userInfo);
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
            return userInfoMapper.userList(userInfo);
        }catch (Exception e){
            logger.error("查询用户列表失败：{}", e.getMessage(),e);
            return null;
        }
    }

    @Override
    public UserInfo getUserByOpenId(String openId) {
        Assert.notNull(openId,"openId is null");
        try {
            return userInfoMapper.getRecordByOpenId(openId);
        }catch (Exception e){
            logger.error("获取用户信息失败：{}", e.getMessage(),e);
            return null;
        }
    }

    /**
     * 条件查询
     * @param userInfo
     * @return
     */
    @Override
    public UserInfo getOne(UserInfo userInfo) {
        try {
            return userInfoMapper.getOne(userInfo);
        }catch (Exception e){
            logger.error("获取用户信息失败：{}", e.getMessage(),e);
            return null;
        }
    }

    @Override
    public UserInfo updateUser(UserInfo userInfo) {
        Assert.notNull(userInfo,"userInfo is null");
        Assert.notNull(userInfo.getId(),"id is null");
        try {
            userInfoMapper.updateUser(userInfo);
            return userInfo;
        }catch (Exception e){
            logger.error("获取用户信息失败：{}", e.getMessage(),e);
            return null;
        }
    }

}
