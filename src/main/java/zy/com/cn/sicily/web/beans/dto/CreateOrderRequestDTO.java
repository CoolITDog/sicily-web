package zy.com.cn.sicily.web.beans.dto;

import java.io.Serializable;

/**
 * @title: CreateOrderRequestDTO
 * @description: 微信支付调用请求对象
 * @author: zhangyan
 * @date: 2020-08-12 11:25
 * @version: 1.0
 **/
public class CreateOrderRequestDTO implements Serializable {

    /**
     * 当前调起支付的小程序APPID
     */
    private String subAppId;

    /**
     * 微信支付分配的子商户号
     */
    private String subMerchantId;

    /**
     * 商户订单号(由业务方传)
     */
    private String orderCode;

    /**
     * 小程序用户的OPENID
     */
    private String subOpenId;

    /**
     * 商品描述
     */
    private String goodsDesc;

    /**
     * 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
     */
    private String attach;

    /**
     * 订单总金额，只能为整数(单位：分)
     */
    private Integer totalFee;

    /**
     * 订单生成时间，格式为yyyyMMddHHmmss
     */
    private String timeStart;

    /**
     * 订单失效时间，格式为yyyyMMddHHmmss
     */
    private String timeExpire;

    /**
     * 支持IPV4和IPV6两种格式的IP地址。调用微信支付API的机器IP
     */
    private String spbillCreateIp;

    /**
     * 接收微信支付异步通知回调地址
     */
    private String notifyUrl;

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

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getSubOpenId() {
        return subOpenId;
    }

    public void setSubOpenId(String subOpenId) {
        this.subOpenId = subOpenId;
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

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    @Override
    public String toString() {
        return "CreateOrderRequestDTO [subAppId=" + subAppId
                + ", subMerchantId=" + subMerchantId + ", orderCode="
                + orderCode + ", subOpenId=" + subOpenId + ", goodsDesc="
                + goodsDesc + ", attach=" + attach + ", totalFee=" + totalFee
                + ", timeStart=" + timeStart + ", timeExpire=" + timeExpire
                + ", spbillCreateIp=" + spbillCreateIp + ", notifyUrl="
                + notifyUrl + "]" + super.toString();
    }
}
