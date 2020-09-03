package zy.com.cn.sicily.web.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zy.com.cn.sicily.web.mapper.WeChatConfigMapper;
import zy.com.cn.sicily.web.model.WeChatConfig;
import zy.com.cn.sicily.web.service.WeChatConfigService;

/**
 * @title: WeChatConfigServiceImpl
 * @description: 微信配置服务
 * @author: zhangyan
 * @date: 2020-07-24 17:15
 * @version: 1.0
 **/
@Service
public class WeChatConfigServiceImpl implements WeChatConfigService {
    @Autowired
    public WeChatConfigMapper weChatConfigMapper;
    /**
     * 根据配置名称获取配置内容
     *
     * @param configName
     * @return
     */
    @Override
    public WeChatConfig getByName(String configName) {
        try{
            return weChatConfigMapper.getByName(configName);
        }catch (Exception e){
            return null;
        }
    }
}
