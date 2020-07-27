package zy.com.cn.sicily.web.message.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @title: UnifiedOrderResponse
 * @description: 统一下单结果对象
 * @author: zhangyan
 * @date: 2020-07-24 09:33
 * @version: 1.0
 **/
public class UnifiedOrderResponse extends BaseResponse{

    private static final long serialVersionUID = 5118367015346897625L;

    // 以下字段在return_code为SUCCESS的时候有返回

    /**
     * 调用接口提交的终端设备号
     */
    @XStreamAlias("device_info")
    private String deviceInfo;

    // 以下字段在return_code 和result_code都为SUCCESS的时候有返回

    /**
     * 预支付交易会话标识
     */
    @XStreamAlias("prepay_id")
    private String prepayId;

    /**
     * 交易类型:取值如下：JSAPI，NATIVE，APP，WAP
     */
    @XStreamAlias("trade_type")
    private String tradeType;

    /**
     * trade_type=NATIVE时有返回，此url用于生成支付二维码，然后提供给用户进行扫码支付
     */
    @XStreamAlias("code_url")
    private String codeUrl;

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    @Override
    public String toString() {
        return "UnifiedOrderResponse{" +
                "deviceInfo='" + deviceInfo + '\'' +
                ", prepayId='" + prepayId + '\'' +
                ", tradeType='" + tradeType + '\'' +
                ", codeUrl='" + codeUrl + '\'' +
                "} " + super.toString();
    }
}
