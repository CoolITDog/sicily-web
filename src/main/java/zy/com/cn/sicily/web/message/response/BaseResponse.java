package zy.com.cn.sicily.web.message.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang.StringUtils;
import zy.com.cn.sicily.web.beans.enums.TradeCodeEnum;

import java.io.Serializable;

/**
 * @title: BaseResponse
 * @description: 基本http请求响应对象
 * @author: zhangyan
 * @date: 2020-07-24 09:34
 * @version: 1.0
 **/
public class BaseResponse implements Serializable {

    private static final long serialVersionUID = -6958492611008075987L;
    /**
     * 返回状态码:SUCCESS/FAIL
     */
    @XStreamAlias("return_code")
    private String returnCode;

    /**
     * 返回信息，如非空，为错误原因
     */
    @XStreamAlias("return_msg")
    private String returnMsg;

    // 以下字段在return_code为SUCCESS的时候有返回

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
     * 业务结果:SUCCESS/FAIL
     */
    @XStreamAlias("result_code")
    private String resultCode;

    /**
     * 业务结果描述:OK
     */
    @XStreamAlias("result_msg")
    private String resultMsg;

    /**
     * 错误代码
     */
    @XStreamAlias("err_code")
    private String errCode;

    /**
     * 错误代码描述
     */
    @XStreamAlias("err_code_des")
    private String errCodeDes;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

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

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }

    public boolean isSuccess(){
        return TradeCodeEnum.SUCCESS.getValue().equals(returnCode) && TradeCodeEnum.SUCCESS.getValue().equals(resultCode);
    }

    public boolean isFail(){
        return TradeCodeEnum.FAIL.getValue().equals(returnCode) || TradeCodeEnum.FAIL.getValue().equals(resultCode);
    }

    public String getErrMessage(){
        return StringUtils.defaultIfEmpty(getErrCodeDes(), StringUtils.defaultIfEmpty(getReturnMsg(), getResultMsg()));
    }

    @Override
    public String toString() {
        return "BaseResponse [returnCode=" + returnCode + ", returnMsg="
                + returnMsg + ", appId=" + appId + ", merchantId=" + merchantId
                + ", subAppId=" + subAppId + ", subMerchantId=" + subMerchantId
                + ", nonceStr=" + nonceStr + ", sign=" + sign + ", resultCode="
                + resultCode + ", resultMsg=" + resultMsg + ", errCode="
                + errCode + ", errCodeDes=" + errCodeDes + "]";
    }
}
