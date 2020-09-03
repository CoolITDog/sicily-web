package zy.com.cn.sicily.web.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import zy.com.cn.sicily.web.beans.dto.UserInfoDTO;
import zy.com.cn.sicily.web.beans.dto.WechatAuthSettingVO;
import zy.com.cn.sicily.web.model.UserInfo;
import zy.com.cn.sicily.web.service.LoginService;
import zy.com.cn.sicily.web.service.UserInfoService;
import zy.com.cn.sicily.web.utils.AES;
import zy.com.cn.sicily.web.utils.Constants;
import zy.com.cn.sicily.web.utils.SHA1;
import zy.com.cn.sicily.web.utils.WxPKCS7Encoder;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.DigestException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Date;

/**
 * @title: LoginServiceImpl
 * @description: 登录服务实现
 * @author: zhangyan
 * @date: 2020-09-01 14:22
 * @version: 1.0
 **/
@Service
public class LoginServiceImpl implements LoginService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private HttpSession session;
    /**
     * 根据openId登录
     * @param openId
     * @return
     */
    @Override
    public UserInfo loginByOpenId(String openId, String appId) {
        UserInfo userInfo = loadFromCache(openId);
        if(userInfo != null){
            return userInfo;
        }
        userInfo = userInfoService.getUserByOpenId(openId);
        userInfo = decode(userInfo);
        // 缓存微信用户信息
        cache(userInfo, appId);
        return userInfo;
    }

    /**
     * 授权登录
     *
     * @param authSetting
     * @return
     */
    @Override
    public UserInfo authLogin(WechatAuthSettingVO authSetting) {
        logger.info("sessionId:{} WE_APP_SESSION_KEY:{}", session.getId(), session.getAttribute(Constants.WE_APP_SESSION_KEY));
        String sessionKey = session.getAttribute(Constants.WE_APP_SESSION_KEY).toString();
        // sessionKey 和签名一致
        boolean verifyResult = verify(sessionKey, authSetting);
        if(!verifyResult){
            return null;
        }
        // 获取到用户在小程序授权的信息
        UserInfo userInfo = decryptWechatUserInfo(authSetting, sessionKey);
        if(userInfo == null){
            return null;
        }
        logger.info("decrypt wechat userinfo:{}", userInfo);
        // 查询是否是已授权用户
        UserInfo exist = userInfoService.getUserByOpenId(userInfo.getOpenId());
        if(null != exist){
            session.setAttribute(Constants.WE_APP_USERINFO_KEY, exist);
            return exist;
        }
        session.setAttribute(Constants.WE_APP_USERINFO_KEY, userInfo);
        // 保存小程序微信用户信息
        userInfo.setCreatedBy(userInfo.getWechatName());
        userInfo.setCreatedDate(new Date());
        userInfo.setDeletedFlag("N");
        userInfo = userInfoService.insertUserInfo(userInfo);
        session.setAttribute(Constants.SESSION_USERINFO_KEY, userInfo);
        return userInfo;
    }

    /**
     * 授权手机号
     *
     * @param authSetting
     * @return
     */
    @Override
    public UserInfo authPone(WechatAuthSettingVO authSetting) {
        UserInfo exit = (UserInfo) session.getAttribute(Constants.SESSION_USERINFO_KEY);
        if(null == exit){
            return null;
        }
        // 解密手机号
        String sessionKey = session.getAttribute(Constants.WE_APP_SESSION_KEY).toString();
        UserInfo userInfo = decryptWechatPhone(authSetting, sessionKey);
        // 更新用户手机号
        if(null != userInfo){
           // 根据用户
            exit.setMobile(userInfo.getMobile());
            userInfoService.updateUser(exit);
            session.setAttribute(Constants.SESSION_USERINFO_KEY, userInfo);
            String appId = session.getAttribute(Constants.WE_CHAT_ID).toString();
            redisTemplate.opsForHash().delete(Constants.WE_CHAT_USER_INFO.concat(appId), exit.getOpenId());
            return exit;
        }
        return null;
    }

    /**
     * 从缓存中获取微信用户信息
     * @param openId
     * @return
     */
    private UserInfo loadFromCache(String openId){
        try{
            Object appId = redisTemplate.opsForHash().get(Constants.WE_CHAT_OPENID_APPID_REF, openId);
            if(appId == null){
                return null;
            }
            Object userInfo = redisTemplate.opsForHash().get(Constants.WE_CHAT_USER_INFO.concat(appId.toString()), openId);
            if(userInfo == null){
                return null;
            }
            if(userInfo instanceof UserInfo){
                return UserInfo.class.cast(userInfo);
            }
        }catch(Exception e){
            logger.error("get wechat user info from cache error:" + e.getMessage(), e);
        }
        return null;
    }

    /**
     * 解码名称
     * @param userInfo
     * @return
     */
    private UserInfo decode(UserInfo userInfo){
        if(userInfo == null){
            return userInfo;
        }
        if(StringUtils.isNotEmpty(userInfo.getWechatName())){
            try {
                userInfo.setWechatName(URLDecoder.decode(userInfo.getWechatName(),"utf-8"));
            } catch (UnsupportedEncodingException e) {
            }
        }
        return userInfo;
    }

    /**
     * 缓存用户信息
     * @param userInfo
     */
    private void cache(UserInfo userInfo, String appId){
        try{
            if(userInfo == null || StringUtils.isEmpty(userInfo.getOpenId())){
                return;
            }
            redisTemplate.opsForHash().put(Constants.WE_CHAT_OPENID_APPID_REF, userInfo.getOpenId(), appId);
            redisTemplate.opsForHash().put(Constants.WE_CHAT_USER_INFO.concat(appId), userInfo.getOpenId(), userInfo);
        }catch(Exception e){
            logger.error("cache wechat user info error:" + e.getMessage(), e);
        }
    }

    /**
     * 验证签名
     * @param sessionKey
     * @param authSetting
     * @return
     */
    private boolean verify(String sessionKey, WechatAuthSettingVO authSetting){
        String decrypt = authSetting.getRawData().concat(sessionKey);
        String sign = null;
        try {
            sign = SHA1.SHA2(decrypt);
        } catch (DigestException e) {
            e.printStackTrace();
        }
        logger.info("varify weapp user info, decrypt:{} sign:{}, signature:{}", decrypt, sign, authSetting.getSignature());
        return authSetting.getSignature().equals(sign);
    }

    /**
     * 解密用户信息
     * @param authSetting
     * @param sessionKey
     * @return
     */
    private UserInfo decryptWechatUserInfo(WechatAuthSettingVO authSetting, String sessionKey){
        UserInfoDTO userInfo = null;
        AES aes = new AES();
        try {
            byte[] resultByte = aes.decrypt(Base64.decode(authSetting.getEncryptedData()), Base64.decode(sessionKey), Base64.decode(authSetting.getIv()));
            if(null != resultByte && resultByte.length > 0){
                String userInfoStr = new String(WxPKCS7Encoder.decode(resultByte));
                logger.info("decrypt wechat userinfo return:{}", userInfoStr);
                userInfo = JSON.parseObject(userInfoStr, UserInfoDTO.class);
                userInfo.setWechatName(userInfo.getNickName());
            }
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    private UserInfo decryptWechatPhone(WechatAuthSettingVO authSetting, String sessionKey){
        UserInfoDTO userInfo = null;
        AES aes = new AES();
        try {
            byte[] resultByte = aes.decrypt(Base64.decode(authSetting.getEncryptedData()), Base64.decode(sessionKey), Base64.decode(authSetting.getIv()));
            if(null != resultByte && resultByte.length > 0){
                String phoneInfo = new String(WxPKCS7Encoder.decode(resultByte));
                logger.info("decrypt wechat phone return:{}", phoneInfo);
                userInfo = JSON.parseObject(phoneInfo, UserInfoDTO.class);
                userInfo.setMobile(userInfo.getPurePhoneNumber());
            }
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return userInfo;
    }
}
