package zy.com.cn.sicily.web.controller.merchant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import zy.com.cn.sicily.web.beans.ResultEntity;
import zy.com.cn.sicily.web.model.Merchant;
import zy.com.cn.sicily.web.service.MerchantService;

import javax.servlet.http.HttpSession;

/**
 * @title: IndexController
 * @description: PC首页
 * @author: zhangyan
 * @date: 2020-07-28 10:08
 * @version: 1.0
 **/
@RequestMapping("/")
@RestController
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private MerchantService merchantService;

    @GetMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    /**
     * 登录
     * @param session
     * @param accountName
     * @param password
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String login(HttpSession session, String accountName, String password, Model model) throws Exception {
        logger.info("accountName:{}", accountName);
        Merchant merchantInfo = merchantService.getByAccountName(accountName);
        if(null != merchantInfo){
            if(password.equals(merchantInfo.getPassword())){
                session.setAttribute("accountName", accountName);
                model.addAttribute("error", "success");
                return "index";
            }
        }
        model.addAttribute("error", "error");
        return "login";
    }
    /**
     * 登录
     * @return
     */
    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String loginPage() {
        logger.info("登录");
        return "login";
    }
    /**
     * 退出
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public ResultEntity<String> logout(HttpSession session){
        session.invalidate();
        return ResultEntity.success("退出成功！");
    }
}
