package zy.com.cn.sicily.web.config;

/**
 * @title: WeChatRequestUrl
 * @description: 微信api接口url地址
 * @author: zhangyan
 * @date: 2020-07-23 17:42
 * @version: 1.0
 **/
public class WeChatRequestUrl {

    /**
     * code 换取 session_key
     */
    public final static String GTX_JS_CODE_2_SESSION_PATH = "https://api.weixin.qq.com/sns/jscode2session?";
    /**
     * 统一下单
     */
    public final static String UNIFIED_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
}
