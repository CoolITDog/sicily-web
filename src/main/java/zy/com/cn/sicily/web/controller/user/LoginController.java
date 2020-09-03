package zy.com.cn.sicily.web.controller.user;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import zy.com.cn.sicily.web.beans.ResultEntity;
import zy.com.cn.sicily.web.beans.dto.JsCodeSessionDTO;
import zy.com.cn.sicily.web.beans.dto.WechatAuthSettingVO;
import zy.com.cn.sicily.web.beans.enums.DefaultErrorEnum;
import zy.com.cn.sicily.web.controller.BaseController;
import zy.com.cn.sicily.web.manager.WechatAccessManager;
import zy.com.cn.sicily.web.model.Merchant;
import zy.com.cn.sicily.web.model.UserInfo;
import zy.com.cn.sicily.web.service.LoginService;
import zy.com.cn.sicily.web.service.MerchantService;
import zy.com.cn.sicily.web.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * LoginController
 * @description 登录控制器
 * @author zhangyan
 * @date 2020-03-12 16:44
 * @version 1.0
 **/
@Controller
@RequestMapping("login")
public class LoginController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private WechatAccessManager wechatAccessManager;

    @Autowired
    private HttpSession session;

    @Autowired
    private LoginService loginService;
    /**
     * 小程序授权
     * @param code 授权凭证码
     * @param appId
     * @return userInfo
     */
    @RequestMapping("jsCode/login")
    @ResponseBody
    public ResultEntity<UserInfo> getCode(@RequestParam(name = "code") String code,
                                          @RequestParam(name = "appId") String appId){
        logger.info("auth code:{}, appId：{}", code, appId);
        try{
            Merchant merchant = merchantService.getMerchant();
            // 获取到openId
            JsCodeSessionDTO codeSession = wechatAccessManager.jsCode2Session(code, appId, merchant.getAppSecret());
            if(null == codeSession){
                logger.error("auth login error:{}");
                return ResultEntity.error("通过code获取session失败");
            }
            session.setAttribute(Constants.WE_APP_SESSION_KEY, codeSession.getSessionKey());
            logger.info("sessionId:{} WE_APP_SESSION_KEY:{}", session.getId(), session.getAttribute(Constants.WE_APP_SESSION_KEY));
            session.setAttribute(Constants.WE_CHAT_ID, appId);
            // 查询是否有该用户
            UserInfo userInfo = loginService.loginByOpenId(codeSession.getOpenid(), appId);
            if(null == userInfo){
                return ResultEntity.error(DefaultErrorEnum.DATA_NULL.getValue(), DefaultErrorEnum.DATA_NULL.getDisplayName());
            }
            session.setAttribute(Constants.SESSION_USERINFO_KEY, userInfo);
            return ResultEntity.success(userInfo);
        }catch (Exception e){
            logger.error("auth login error:{}", e.getMessage(), e);
            return ResultEntity.error(e.getMessage());
        }
    }

    /**
     * 授权登录
     * @param authSetting
     * @return
     */
    @RequestMapping("auth/login")
    @ResponseBody
    public ResultEntity<UserInfo> authLogin(@RequestBody WechatAuthSettingVO authSetting){
        logger.info("auth/login WechatAuthSettingVO:{}", authSetting);
        if(authSetting == null){
            return ResultEntity.error("必要参数为空");
        }
        if(StringUtils.isEmpty(authSetting.getEncryptedData())
                || StringUtils.isEmpty(authSetting.getIv())
                || StringUtils.isEmpty(authSetting.getSignature())
                || StringUtils.isEmpty(authSetting.getRawData())){
            return ResultEntity.error("必要参数为空");
        }
        logger.info("sessionId:{} WE_APP_SESSION_KEY:{}", session.getId(), session.getAttribute(Constants.WE_APP_SESSION_KEY));
        return ResultEntity.success(loginService.authLogin(authSetting));
    }

    /**
     * 授权手机号
     * @param authSetting
     * @return
     */
    @RequestMapping("auth/phone")
    @ResponseBody
    public ResultEntity<UserInfo> authPhone(@RequestBody WechatAuthSettingVO authSetting){
        logger.info("authSetting:{}", authSetting);
        // 解密得出用户手机号
        return ResultEntity.success(loginService.authPone(authSetting));
    }
}
