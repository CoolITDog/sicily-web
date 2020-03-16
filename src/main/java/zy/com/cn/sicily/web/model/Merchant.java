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

    @Override
    public String toString() {
        return "Merchant{" +
                "phone='" + phone + '\'' +
                ", appId='" + appId + '\'' +
                ", appSecret='" + appSecret + '\'' +
                "} " + super.toString();
    }
}
