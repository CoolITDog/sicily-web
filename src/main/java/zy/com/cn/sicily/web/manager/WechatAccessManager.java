package zy.com.cn.sicily.web.manager;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import zy.com.cn.sicily.web.beans.dto.JsCodeSessionDTO;
import zy.com.cn.sicily.web.config.WeChatRequestUrl;
import zy.com.cn.sicily.web.http.HttpClient;

/**
 * @title: WechatAccessManager
 * @description: 微信登录
 * @author: zhangyan
 * @date: 2020-03-13 15:45
 * @version: 1.0
 **/
@Component
public class WechatAccessManager {

    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * code 换取 session_key
     * @param code 登录时获取的 code
     * @return
     * @author Alvin
     * @date 2019年4月18日 下午4:59:39
     */
    public JsCodeSessionDTO jsCode2Session(String code, String appId, String secret){
        try {
            Assert.notNull(code, "code is null");
            Assert.notNull(appId, "appId is null");
            Assert.notNull(secret, "secret is null");
            String httpUrl = WeChatRequestUrl.GTX_JS_CODE_2_SESSION_PATH;
            httpUrl = httpUrl.concat("appid=").concat(appId).concat("&secret=").concat(secret).concat("&js_code=").concat(code).concat("&grant_type=authorization_code");
            logger.info("httpUrl:{}", httpUrl);
            String response = HttpClient.doGet(httpUrl);
            logger.info("js code to session response:{}", response);
            JSONObject jsonObject = JSONObject.fromObject(response);
            JsCodeSessionDTO sessionDTO = (JsCodeSessionDTO) JSONObject.toBean(jsonObject, JsCodeSessionDTO.class);
            logger.info("js code to dto response:{}", sessionDTO);
            return sessionDTO;
        } catch (Exception e) {
            logger.error("js code to session error:" + e.getMessage(), e);
        }
        return null;
    }
}
