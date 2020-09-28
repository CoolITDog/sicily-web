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

    /**
     * 发送服务消息
     */
//    public final static String UNIFORM_MESSAGE_SEND = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token=";
    public final static String UNIFORM_MESSAGE_SEND = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=";
    /**
     * 获取access_token
     */
    public static final String TOKEN_PATH = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";

    /**
     * 获取订阅消息模板列表
     */
    public static final String GET_TEMPLATE_LIST_PATH = "https://api.weixin.qq.com/wxaapi/newtmpl/gettemplate?access_token=";
}
