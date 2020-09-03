package zy.com.cn.sicily.web.model;

import javax.persistence.Table;
import java.util.Date;

/**
 * @title: WechatTradeOrder
 * @description: 微信交易订单记录
 * @author: zhangyan
 * @date: 2020-08-12 16:44
 * @version: 1.0
 **/
@Table(name = "sicily_wechat_trade_order")
public class WechatTradeOrder extends BaseModel {
    /**
     * APP_ID:服务商应用ID(微信分配的公众账号ID)
     */
    private String appId;

    /**
     * MERCHANT_ID:商户号(微信支付分配的商户号)
     */
    private String merchantId;

    /**
     * SUB_APP_ID:当前调起支付的小程序APPID
     */
    private String subAppId;

    /**
     * SUB_MERCHANT_ID:微信支付分配的子商户号
     */
    private String subMerchantId;

    /**
     * OUT_TRADE_NO:商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一
     */
    private String outTradeNo;

    /**
     * ORDER_CODE:商户订单号(由业务方传)
     */
    private String orderCode;

    /**
     * TRANSACTION_ID:微信交易号
     */
    private String transactionId;

    /**
     * OPEN_ID:用户OPENID
     */
    private String openId;

    /**
     * SUB_OPEN_ID:小程序用户的OPENID
     */
    private String subOpenId;

    /**
     * NONCE_STR:随机字符串，不长于32位
     */
    private String nonceStr;

    /**
     * GOODS_DESC:商品描述
     */
    private String goodsDesc;

    /**
     * ATTACH:附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
     */
    private String attach;

    /**
     * TOTAL_FEE:订单总金额，只能为整数(单位：分)
     */
    private Integer totalFee;

    /**
     * TRADE_STATUE:交易状态(SUCCESS—支付成功；REFUND—转入退款；NOTPAY—未支付；CLOSED—已关闭；REVOKED—已撤销；USERPAYING--用户支付中；PAYERROR--支付失败)
     */
    private String tradeStatus;

    /**
     * TRADE_SNAPSHOT:交易响应快照
     */
    private String tradeSnapshot;

    /**
     * TIME_START:订单生成时间，格式为yyyyMMddHHmmss
     */
    private String timeStart;

    /**
     * TIME_EXPIRE:订单失效时间，格式为yyyyMMddHHmmss
     */
    private String timeExpire;

    /**
     * TRADE_TIME:交易时间
     */
    private Date tradeTime;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getSubAppId() {
        return subAppId;
    }

    public void setSubAppId(String subAppId) {
        this.subAppId = subAppId;
    }

    public String getSubMerchantId() {
        return subMerchantId;
    }

    public void setSubMerchantId(String subMerchantId) {
        this.subMerchantId = subMerchantId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getTradeSnapshot() {
        return tradeSnapshot;
    }

    public void setTradeSnapshot(String tradeSnapshot) {
        this.tradeSnapshot = tradeSnapshot;
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

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    @Override
    public String toString() {
        return "WechatTradeOrder{" +
                "appId='" + appId + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", subAppId='" + subAppId + '\'' +
                ", subMerchantId='" + subMerchantId + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", orderCode='" + orderCode + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", openId='" + openId + '\'' +
                ", subOpenId='" + subOpenId + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", goodsDesc='" + goodsDesc + '\'' +
                ", attach='" + attach + '\'' +
                ", totalFee=" + totalFee +
                ", tradeStatus='" + tradeStatus + '\'' +
                ", tradeSnapshot='" + tradeSnapshot + '\'' +
                ", timeStart='" + timeStart + '\'' +
                ", timeExpire='" + timeExpire + '\'' +
                ", tradeTime=" + tradeTime +
                "} " + super.toString();
    }
}
