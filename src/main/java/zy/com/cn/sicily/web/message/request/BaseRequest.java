package zy.com.cn.sicily.web.message.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * @title: BaseRequest
 * @description: 基本http请求对象
 * @author: zhangyan
 * @date: 2020-07-24 09:49
 * @version: 1.0
 **/
public class BaseRequest implements Serializable {

    private static final long serialVersionUID = 5894883254636489984L;

    /**
     * 公众账号ID:微信分配的公众账号ID
     */
    @XStreamAlias("appid")
    private String appId;

    /**
     * 商户号:微信支付分配的商户号
     */
    @XStreamAlias("mch_id")
    private String merchantId;

    /**
     * 当前调起支付的小程序APPID
     */
    @XStreamAlias("sub_appid")
    private String subAppId;

    /**
     * 微信支付分配的子商户号
     */
    @XStreamAlias("sub_mch_id")
    private String subMerchantId;

    /**
     * 随机字符串:不长于32位
     */
    @XStreamAlias("nonce_str")
    private String nonceStr;

    /**
     * 签名
     */
    @XStreamAlias("sign")
    private String sign;

    /**
     * 签名类型,目前支持HMAC-SHA256和MD5，默认为MD5
     */
    @XStreamAlias("sign_type")
    private String signType;

    /**
     * 商户订单号:商户系统内部的订单号,32个字符内、可包含字母
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

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

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    @Override
    public String toString() {
        return "BaseRequest [appId=" + appId + ", merchantId=" + merchantId
                + ", subAppId=" + subAppId + ", subMerchantId=" + subMerchantId
                + ", nonceStr=" + nonceStr + ", sign=" + sign + ", signType="
                + signType + ", outTradeNo=" + outTradeNo + "]";
    }
}
