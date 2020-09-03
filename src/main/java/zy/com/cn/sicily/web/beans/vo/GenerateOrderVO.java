package zy.com.cn.sicily.web.beans.vo;

import zy.com.cn.sicily.web.model.OrderFood;

import java.io.Serializable;
import java.util.List;

/**
 * @title: GenerateOrderVO
 * @description: 创建订单交互对象
 * @author: zhangyan
 * @date: 2020-08-12 10:51
 * @version: 1.0
 **/
public class GenerateOrderVO implements Serializable {

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 发货方式：1：自提，2：送货上门
     */
    private Integer attainWay;

    /**
     * 订单金额
     */
    private Double price;

    /**
     * 种类个数
     */
    private Integer classNum;

    /**
     * 食品个数
     */
    private Integer foodNum;

    /**
     * 地址
     */
    private String address;

    /**
     * 备注
     */
    private String remark;

    /**
     * 订单状态：1：已下单，2：已接单，3：已取消，4：完成
     */
    private Integer status;

    /**
     * 选购列表
     */
    private List<OrderFood> foodList;

    /**
     * 小程序openId
     */
    private String openId;

    /**
     * 商品名
     */
    private String productName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAttainWay() {
        return attainWay;
    }

    public void setAttainWay(Integer attainWay) {
        this.attainWay = attainWay;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    public Integer getFoodNum() {
        return foodNum;
    }

    public void setFoodNum(Integer foodNum) {
        this.foodNum = foodNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<OrderFood> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<OrderFood> foodList) {
        this.foodList = foodList;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "GenerateOrderVO{" +
                "orderNo='" + orderNo + '\'' +
                ", userId=" + userId +
                ", attainWay=" + attainWay +
                ", price=" + price +
                ", classNum=" + classNum +
                ", foodNum=" + foodNum +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                ", foodList=" + foodList +
                ", openId='" + openId + '\'' +
                ", productName='" + productName + '\'' +
                '}' + super.toString();
    }
}
