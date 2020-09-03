package zy.com.cn.sicily.web.service;

import zy.com.cn.sicily.web.beans.dto.WechatAuthSettingVO;
import zy.com.cn.sicily.web.model.UserInfo;

/**
 * @title: LoginService
 * @description: 登录服务
 * @author: zhangyan
 * @date: 2020-09-01 14:21
 * @version: 1.0
 **/
public interface LoginService {
    /**
     * 根据openId登录
     * @param openId
     * @return
     */
    UserInfo loginByOpenId(String openId, String appId);

    /**
     * 授权登录
     * @param authSetting
     * @return
     */
    UserInfo authLogin(WechatAuthSettingVO authSetting);

    /**
     * 授权手机号
     * @param authSetting
     * @return
     */
    UserInfo authPone(WechatAuthSettingVO authSetting);
}
