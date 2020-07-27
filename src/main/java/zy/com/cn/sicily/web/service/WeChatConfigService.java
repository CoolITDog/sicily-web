package zy.com.cn.sicily.web.service;

import zy.com.cn.sicily.web.model.WeChatConfig;

public interface WeChatConfigService {
    /**
     * 根据配置名称获取配置内容
     * @param configName
     * @return
     */
    WeChatConfig getByName(String configName);
}
