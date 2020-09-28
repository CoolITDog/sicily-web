package zy.com.cn.sicily.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import zy.com.cn.sicily.web.manager.WeChatMessageManager;
import zy.com.cn.sicily.web.manager.WechatAccessManager;
import zy.com.cn.sicily.web.model.Merchant;
import zy.com.cn.sicily.web.model.UserInfo;
import zy.com.cn.sicily.web.service.MerchantService;
import zy.com.cn.sicily.web.service.ShopService;
import zy.com.cn.sicily.web.service.UserInfoService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @title: ManagerTest
 * @description: 微信接口调用测试用例
 * @author: zhangyan
 * @date: 2020-09-04 11:20
 * @version: 1.0
 **/
public class ManagerTest extends DemoTakeTaskApplicationTests{

    @Autowired
    private WechatAccessManager wechatAccessManager;
    @Autowired
    private WeChatMessageManager weChatMessageManager;
    @Autowired
    private ShopService shopService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private UserInfoService userInfoService;
    @Test
    public void list (){
        String accessToken = wechatAccessManager.getAccessToken("wxbf9c2f4f093a13bc","dc5399a8eb34f68748ba2527c9b6a80d");
        weChatMessageManager.getTemplateList(accessToken);
    }
    @Test
    public void test(){
        // 获取商家的openId
        Merchant merchant = merchantService.getMerchant();
        UserInfo condition = new UserInfo();
        condition.setMobile(merchant.getPhone());
        UserInfo userInfo = userInfoService.getOne(condition);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM");
        //openId, info.getId(), info.getPrice(), info.getRemark(),user.getWechatName(),sdf.format(info.getCreatedDate())
        shopService.sendMessage(1,665592941, 6.55,"少冰","阿丙",sdf.format(new Date()));
    }
}
