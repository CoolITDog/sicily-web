package zy.com.cn.sicily.web.model;

import javax.persistence.Table;

/**
 * @title: Merchant
 * @description: 商户信息实体对象
 * @author: zhangyan
 * @date: 2020-03-13 16:54
 * @version: 1.0
 **/
@Table(name = "sicily_merchant")
public class Merchant extends BaseModel{
    private static final long serialVersionUID = 1L;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 小程序APPId
     */
    private String appId;

    /**
     * 小程序秘钥
     */
    private String appSecret;

    /**
     * 密码
     */
    private String password;

    /**
     * 特约商户号
     */
    private Integer merchantId;

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "phone='" + phone + '\'' +
                ", appId='" + appId + '\'' +
                ", appSecret='" + appSecret + '\'' +
                ", merchantId='" + merchantId + '\'' +
                "} " + super.toString();
    }
}
