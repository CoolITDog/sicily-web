package zy.com.cn.sicily.web.service;

import zy.com.cn.sicily.web.model.WechatMessageConfig;

/**
 * @title: WechatMessageConfigService
 * @description: 微信模板消息服务
 * @author: zhangyan
 * @date: 2020-09-04 10:53
 * @version: 1.0
 **/
public interface WechatMessageConfigService {
    /**
     * 根据消息类型查找模板消息
     * @param msgType
     * @return
     */
    WechatMessageConfig getByMsgType(Integer msgType);
}
