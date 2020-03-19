package zy.com.cn.sicily.web.controller;

import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import zy.com.cn.sicily.web.beans.ResultEntity;
import zy.com.cn.sicily.web.beans.dto.JsCodeSessionDTO;
import zy.com.cn.sicily.web.beans.dto.UserInfoDTO;
import zy.com.cn.sicily.web.beans.enums.DefaultErrorEnum;
import zy.com.cn.sicily.web.manager.WechatAccessManager;
import zy.com.cn.sicily.web.model.Merchant;
import zy.com.cn.sicily.web.model.UserInfo;
import zy.com.cn.sicily.web.service.MerchantService;
import zy.com.cn.sicily.web.service.UserInfoService;
import zy.com.cn.sicily.web.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @title: LoginController
 * @description: 登录控制器
 * @author: zhangyan
 * @date: 2020-03-12 16:44
 * @version: 1.0
 **/
@Controller
@RequestMapping("login")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private WechatAccessManager wechatAccessManager;

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private  HttpSession session;

    /**
     * 小程序授权
     * @param code
     * @return
     */
    @RequestMapping("getCode")
    @ResponseBody
    public ResultEntity<UserInfo> getCode(String code){
        logger.info("auth code:{}", code);
        try{
            Merchant merchant = merchantService.getMerchant();
            // 获取到openId
            JsCodeSessionDTO codeSession = wechatAccessManager.jsCode2Session(code, merchant.getAppId(), merchant.getAppSecret());
            session.setAttribute(Constants.WEAPP_SESSION_KEY, codeSession.getSession_key());
            if( null == session.getAttribute(Constants.OPEN_ID_SESSION_KEY)){
                session.setAttribute(Constants.OPEN_ID_SESSION_KEY, codeSession.getOpenid());
            }
            // 查询是否有改用户
            UserInfo user = userInfoService.getUserByOpenId(codeSession.getOpenid());
            if(null != user){
                return ResultEntity.success(user);
            }
            return ResultEntity.error(DefaultErrorEnum.DATA_NULL.getValue(), DefaultErrorEnum.DATA_NULL.getDisplayName());
        }catch (Exception e){
            logger.error("auth login error:{}", e.getMessage(), e);
            return ResultEntity.error(e.getMessage());
        }
    }

    /**
     * 保存用户授权信息
     * @param userInfoDTO
     * @return
     */
    @RequestMapping("saveUser")
    @ResponseBody
    public ResultEntity<UserInfo> saveUser(UserInfoDTO userInfoDTO){
        logger.info("saveUser info:{}", userInfoDTO);
        Assert.notNull(userInfoDTO, "userInfo is null");
        try{
            List<UserInfo> exist = userInfoService.listUsers(userInfoDTO);
            if(null != exist && exist.size() > 0){
                return ResultEntity.success(exist.get(0));
            }
            String openId = (String) session.getAttribute(Constants.OPEN_ID_SESSION_KEY);
            logger.info("openId :{}", openId);
            userInfoDTO.setOpenId(openId);
            UserInfo user = userInfoService.insertUserInfo(userInfoDTO);
            if(null != user){
                return ResultEntity.success(user);
            }
            return ResultEntity.error(DefaultErrorEnum.SERVICE_ERROR.getValue(), DefaultErrorEnum.SERVICE_ERROR.getDisplayName());
        }catch (Exception e){
            logger.error("saveUser error:{}", e.getMessage(), e);
            return ResultEntity.error(e.getMessage());
        }
    }
}
