package zy.com.cn.demo.take.task.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zy.com.cn.demo.take.task.beans.ResultEntity;
import zy.com.cn.demo.take.task.manager.WechatAccessManager;
import zy.com.cn.demo.take.task.model.Merchant;
import zy.com.cn.demo.take.task.service.MerchantService;

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
    private  MerchantService merchantService;

    @Autowired
    private WechatAccessManager wechatAccessManager;
    /**
     * 小程序授权
     * @param code
     * @return
     */
    @GetMapping("auth")
    @ResponseBody
    public ResultEntity<String> authLogin(String code){
        logger.info("authlogin:{}", code);
        try{
            Merchant merchant = merchantService.getMerchanr();
            String res = wechatAccessManager.jsCode2Session(code, merchant.getAppId(), merchant.getAppSecret());
            // 获取到openId
            return ResultEntity.success(res);
        }catch (Exception e){
            logger.error("authlogin error:{}", e.getMessage(), e);
            return ResultEntity.error(e.getMessage());
        }
    }

}
