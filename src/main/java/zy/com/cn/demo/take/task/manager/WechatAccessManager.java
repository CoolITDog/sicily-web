package zy.com.cn.demo.take.task.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import zy.com.cn.demo.take.task.beans.dto.JsCodeSessionDTO;
import zy.com.cn.demo.take.task.http.HttpClient;

import java.util.HashMap;
import java.util.Map;

/**
 * @title: WechatAccessManager
 * @description: 微信登录
 * @author: zhangyan
 * @date: 2020-03-13 15:45
 * @version: 1.0
 **/
@Component
public class WechatAccessManager {

    /**
     * 72、code 换取 session_key
     */
    public final static String GTX_JS_CODE_2_SESSION_PATH = "https://api.weixin.qq.com/sns/jscode2session?";

    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * code 换取 session_key
     * @param code 登录时获取的 code
     * @return
     * @author Alvin
     * @date 2019年4月18日 下午4:59:39
     */
    public String jsCode2Session(String code, String appId, String secret){
        try {
            Assert.notNull(code, "code is null");
            Assert.notNull(appId, "appId is null");
            Assert.notNull(secret, "secret is null");
            String httpUrl = GTX_JS_CODE_2_SESSION_PATH;
            httpUrl.concat("appid=").concat(appId).concat("&secret=").concat(secret).concat("&js_code=").concat(code).concat("&grant_type=authorization_code");
            logger.info("httpUrl:{}", httpUrl);
            String response = HttpClient.doGet(httpUrl);
            logger.info("js code to session response:{}", response);
            return response;
        } catch (Exception e) {
            logger.error("js code to session error:" + e.getMessage(), e);
        }
        return null;
    }
}
