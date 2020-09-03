package zy.com.cn.sicily.web.beans.dto;

import zy.com.cn.sicily.web.model.UserInfo;

import java.io.Serializable;

/**
 * @title: WechatAuthSettingVO
 * @description: 微信授权对象信息
 * @author: zhangyan
 * @date: 2020-09-01 15:01
 * @version: 1.0
 **/
@SuppressWarnings("serial")
public class WechatAuthSettingVO implements Serializable {

    /**
     * 包括敏感数据在内的完整用户信息的加密数据
     */
    private String encryptedData;

    /**
     * 加密算法初始向量
     */
    private String iv;

    /**
     * 使用 sha1( rawData + sessionkey ) 得到字符串，用于校验用户信息
     */
    private String signature;

    /**
     * 不包括敏感信息的原始数据字符串，用于计算签名
     */
    private String rawData;

    /**
     * 用户信息对象，不包含 openid 等敏感信息
     */
    private UserInfo userInfo;

    /**
     * 登录CODE
     */
    private String code;

    private String unionId;

    private String openId;

    private String appId;

    private String wxId;

    /**
     * 性别
     */
    private String gender;

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "WechatAuthSettingVO{" +
                "encryptedData='" + encryptedData + '\'' +
                ", iv='" + iv + '\'' +
                ", signature='" + signature + '\'' +
                ", rawData='" + rawData + '\'' +
                ", userInfo=" + userInfo +
                ", code='" + code + '\'' +
                ", unionId='" + unionId + '\'' +
                ", openId='" + openId + '\'' +
                ", appId='" + appId + '\'' +
                ", wxId='" + wxId + '\'' +
                ", gender='" + gender + '\'' +
                '}' + super.toString();
    }
}
