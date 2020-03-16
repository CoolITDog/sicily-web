package zy.com.cn.sicily.web.service;

import zy.com.cn.sicily.web.model.UserInfo;

import java.util.List;

/**
 * @title: UserInfoService
 * @description: 用户信息服务层
 * @author: zhangyan
 * @date: 2020-03-12 10:55
 * @version: 1.0
 **/
public interface UserInfoService {

    /**
     * 新增用户信息
     * @param userInfo
     * @return
     */
    UserInfo insertUserInfo(UserInfo userInfo);

    /**
     * 查询用户列表
     * @param userInfo
     * @return
     */
    List<UserInfo> listUsers(UserInfo userInfo);
}
