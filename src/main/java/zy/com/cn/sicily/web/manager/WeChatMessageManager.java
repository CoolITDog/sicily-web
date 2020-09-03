package zy.com.cn.sicily.web.manager;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import zy.com.cn.sicily.web.config.WeChatRequestUrl;
import zy.com.cn.sicily.web.http.HttpClient;

/**
 * @title: WeChatMessageManager
 * @description: 微信消息发送
 * @author: zhangyan
 * @date: 2020-09-03 16:16
 * @version: 1.0
 **/
@Component
public class WeChatMessageManager {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 发送统一服务消息
     * @param accessToken
     * @param templateMessage
     * @return
     */
    public String uniformMessageSend(String accessToken,String templateMessage){
        logger.info("sendMessage.templateMessage:{}",templateMessage);
        try {
            String url = WeChatRequestUrl.UNIFORM_MESSAGE_SEND + accessToken;
            logger.info("sendMessage url:{}", url);
            String json = HttpClient.doPost(url, templateMessage);
            logger.info("sendMessage response json:{}", json);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

}
