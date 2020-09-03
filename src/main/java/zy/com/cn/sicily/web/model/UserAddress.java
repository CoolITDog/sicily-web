package zy.com.cn.sicily.web.model;

import javax.persistence.Table;

/**
 * @title: UserAddress
 * @description: 用户地址
 * @author: zhangyan
 * @date: 2020-09-03 15:44
 * @version: 1.0
 **/
@Table(name = "sicily_user_address")
public class UserAddress extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户微信授权手机号
     */
    private String mobile;

    /**
     * 收货人姓名
     */
    private String attactName;

    /**
     * 联系电话
     */
    private String attactMobile;

    /**
     * 收货地址
     */
    private String attactAddress;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAttactName() {
        return attactName;
    }

    public void setAttactName(String attactName) {
        this.attactName = attactName;
    }

    public String getAttactMobile() {
        return attactMobile;
    }

    public void setAttactMobile(String attactMobile) {
        this.attactMobile = attactMobile;
    }

    public String getAttactAddress() {
        return attactAddress;
    }

    public void setAttactAddress(String attactAddress) {
        this.attactAddress = attactAddress;
    }

    @Override
    public String toString() {
        return "UserAddress{" +
                "userId=" + userId +
                ", mobile='" + mobile + '\'' +
                ", attactName='" + attactName + '\'' +
                ", attactMobile='" + attactMobile + '\'' +
                ", attactAddress='" + attactAddress + '\'' +
                "} " + super.toString();
    }
}
