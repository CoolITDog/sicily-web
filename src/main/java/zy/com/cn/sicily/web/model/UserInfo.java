package zy.com.cn.sicily.web.model;

import javax.persistence.Table;

/**
 * @title: UserInfo
 * @description: 用户信息实体对象
 * @author: zhangyan
 * @date: 2020-03-12 11:53
 * @version: 1.0
 **/
@Table(name = "sicily_user_info")
public class UserInfo extends  BaseModel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 小程序用户openId
     */
    private String openId;

    /**
     * 微信名称
     */
    private String wechatName;

    /**
     * 微信头像
     */
    private String avatarUrl;

    /**
     * 手机号
     */
    private String mobile;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getWechatName() {
        return wechatName;
    }

    public void setWechatName(String wechatName) {
        this.wechatName = wechatName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "openId='" + openId + '\'' +
                ", wechatName='" + wechatName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", mobile='" + mobile + '\'' +
                "} " + super.toString();
    }
}
