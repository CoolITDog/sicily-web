package zy.com.cn.sicily.web.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import zy.com.cn.sicily.web.beans.dto.JsCodeSessionDTO;
import zy.com.cn.sicily.web.config.WeChatRequestUrl;
import zy.com.cn.sicily.web.http.HttpClient;
import zy.com.cn.sicily.web.utils.Constants;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    final String ERR_CODE = "errcode";

    /**
     * code 换取 session_key
     * @param code 登录时获取的 code
     * @return
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
            logger.info("jsCode to session response:{}", response);
            JsCodeSessionDTO sessionDTO = JSON.parseObject(response,JsCodeSessionDTO.class);
            logger.info("jsCode to dto response:{}", sessionDTO);
            return sessionDTO;
        } catch (Exception e) {
            logger.error("js code to session error:" + e.getMessage(), e);
        }
        return null;
    }

    /**
     * 获取基础凭证信息
     * @Description:
     * @param appId
     * @param appSecret
     * @return
     */
    public String getAccessToken(String appId,String appSecret){
        // 授权方接口调用凭据(有效期2小时)
        Object accessToken = redisTemplate.opsForValue().get(Constants.AUTHORIZE_ACCESS_TOKEN);
        if(null!=accessToken){
            return accessToken.toString();
        }
        try {
            String url = MessageFormat.format(WeChatRequestUrl.TOKEN_PATH,appId,appSecret);
            String response = HttpClient.doGet(url);
            logger.info("getAccessToken response:{}", response);
            JSONObject object = JSONObject.parseObject(response);
            Integer errcode = object.getInteger(ERR_CODE);
            if(errcode != null && errcode != 0){
                logger.error("get authorize access token error:{}" ,object.getString("errmsg"));
                return null;
            }
            accessToken = object.getString("access_token");
            int expiresIn = object.getIntValue("expires_in");
            // 授权方接口调用凭据(有效期2小时)
            redisTemplate.opsForValue().set(Constants.AUTHORIZE_ACCESS_TOKEN, accessToken, expiresIn - 600, TimeUnit.SECONDS);
            return accessToken.toString();
        } catch (Exception e) {
            logger.error("get authorize access token error:" + e.getMessage(), e);
            throw null;
        }
    }
}
