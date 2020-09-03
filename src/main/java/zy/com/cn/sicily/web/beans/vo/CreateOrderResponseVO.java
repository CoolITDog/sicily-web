package zy.com.cn.sicily.web.beans.vo;

import java.io.Serializable;

/**
 * @title: CreateOrderResponseVO
 * @description: 微信支付交互对象
 * @author: zhangyan
 * @date: 2020-08-12 11:13
 * @version: 1.0
 **/
public class CreateOrderResponseVO implements Serializable {
    /**
     * 时间戳从1970年1月1日00:00:00至今的秒数,即当前的时间
     */
    private String timeStamp;
    /**
     * 随机字符串，长度为32个字符以下
     */
    private String nonceStr;
    /**
     * 统一下单接口返回的 prepay_id 参数值
     */
    private String prepayId;
    /**
     * 签名算法，暂支持 MD5
     */
    private String signType;
    /**
     * 签名
     */
    private String paySign;
    /**
     *交易系统内部订单号
     */
    private String outTradeNo;

    public String getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
    public String getNonceStr() {
        return nonceStr;
    }
    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }
    public String getPrepayId() {
        return prepayId;
    }
    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }
    public String getSignType() {
        return signType;
    }
    public void setSignType(String signType) {
        this.signType = signType;
    }
    public String getPaySign() {
        return paySign;
    }
    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }
    public String getOutTradeNo() {
        return outTradeNo;
    }
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
    @Override
    public String toString() {
        return "CreateOrderResponseDTO [timeStamp=" + timeStamp + ", nonceStr="
                + nonceStr + ", prepayId=" + prepayId + ", signType="
                + signType + ", paySign=" + paySign + ", outTradeNo="
                + outTradeNo + "]" + super.toString();
    }
}
