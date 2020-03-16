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
     * 微信名称
     */
    private String wechatName;

    /**
     * 微信头像
     */
    private String wechatLogo;

    /**
     * 手机号
     */
    private String mobile;

    public String getWechatName() {
        return wechatName;
    }

    public void setWechatName(String wechatName) {
        this.wechatName = wechatName;
    }

    public String getWechatLogo() {
        return wechatLogo;
    }

    public void setWechatLogo(String wechatLogo) {
        this.wechatLogo = wechatLogo;
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
                "wechatName='" + wechatName + '\'' +
                ", wechatLogo='" + wechatLogo + '\'' +
                ", mobile='" + mobile + '\'' +
                "} " + super.toString();
    }
}
