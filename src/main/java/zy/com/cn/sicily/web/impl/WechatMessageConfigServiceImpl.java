package zy.com.cn.sicily.web.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zy.com.cn.sicily.web.mapper.WechatMessageConfigMapper;
import zy.com.cn.sicily.web.model.WechatMessageConfig;
import zy.com.cn.sicily.web.service.WechatMessageConfigService;

/**
 * @title: WechatMessageConfigService
 * @description: 微信模板消息服务
 * @author: zhangyan
 * @date: 2020-09-04 10:53
 * @version: 1.0
 **/
@Service
public class WechatMessageConfigServiceImpl implements WechatMessageConfigService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private WechatMessageConfigMapper wechatMessageConfigMapper;
    /**
     * 根据消息类型查找模板消息
     *
     * @param msgType
     * @return
     */
    @Override
    public WechatMessageConfig getByMsgType(Integer msgType) {
        try{
            return wechatMessageConfigMapper.getByMsgType(msgType);
        }catch(Exception e){
            logger.error("wechatMessageConfigMapper.getByMsgType error:{}", e.getMessage(), e);
            return null;
        }
    }
}
