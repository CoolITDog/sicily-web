package zy.com.cn.sicily.web.message.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @title: UnifiedOrderRequest
 * @description: 统一下单请求对象
 * @author: zhangyan
 * @date: 2020-07-24 09:50
 * @version: 1.0
 **/
public class UnifiedOrderRequest extends BaseRequest {

    private static final long serialVersionUID = 5894883254636489984L;

    /**
     * 商品描述:商品或支付单简要描述
     */
    @XStreamAlias("body")
    private String body;

    /**
     * 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
     */
    @XStreamAlias("attach")
    private String attach;

    /**
     * 总金额:订单总金额，只能为整数，单位为【分】
     */
    @XStreamAlias("total_fee")
    private int totalFee;

    /**
     * 订单生成时间，格式为yyyyMMddHHmmss
     */
    @XStreamAlias("time_start")
    private String timeStart;

    /**
     * 订单失效时间，格式为yyyyMMddHHmmss
     */
    @XStreamAlias("time_expire")
    private String timeExpire;

    /**
     * 终端IP:APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
     */
    @XStreamAlias("spbill_create_ip")
    private String spbillCreateIp;

    /**
     * 通知地址:接收微信支付异步通知回调地址
     */
    @XStreamAlias("notify_url")
    private String notifyUrl;

    /**
     * 交易类型:取值如下：JSAPI，NATIVE，APP，WAP
     */
    @XStreamAlias("trade_type")
    private String tradeType;

    /**
     * 指定支付方式:no_credit--指定不能使用信用卡支付
     */
    @XStreamAlias("limit_pay")
    private String limitPay;

    /**
     * 用户标识:trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识
     */
    @XStreamAlias("openid")
    private String openId;

    /**
     * trade_type=JSAPI，此参数必传，用户在子商户appid下的唯一标识。openid和sub_openid可以选传其中之一，如果选择传sub_openid,则必须传sub_appid
     */
    @XStreamAlias("sub_openid")
    private String subOpenId;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getLimitPay() {
        return limitPay;
    }

    public void setLimitPay(String limitPay) {
        this.limitPay = limitPay;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSubOpenId() {
        return subOpenId;
    }

    public void setSubOpenId(String subOpenId) {
        this.subOpenId = subOpenId;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    @Override
    public String toString() {
        return "UnifiedOrderRequest [body=" + body + ", attach=" + attach
                + ", totalFee=" + totalFee + ", timeStart=" + timeStart
                + ", timeExpire=" + timeExpire + ", spbillCreateIp="
                + spbillCreateIp + ", notifyUrl=" + notifyUrl + ", tradeType="
                + tradeType + ", limitPay=" + limitPay + ", openId=" + openId
                + ", subOpenId=" + subOpenId + ", toString()="
                + super.toString() + "]";
    }

}
