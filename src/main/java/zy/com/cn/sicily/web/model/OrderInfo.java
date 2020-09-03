package zy.com.cn.sicily.web.model;

import javax.persistence.Table;

/**
 * @title: OrderInfo
 * @description: 订单信息实体对象
 * @author: zhangyan
 * @date: 2020-03-12 11:49
 * @version: 1.0
 **/
@Table(name = "sicily_order_info")
public class OrderInfo extends BaseModel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

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
    private Integer orderStatus;

    /**
     * 支付状态：1:待支付，2:支付中，3:已支付
     */
    private Integer payStatus;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 微信平台充值订单号
     */
    private String outTradeNo;

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "userId=" + userId +
                ", attainWay=" + attainWay +
                ", price=" + price +
                ", classNum=" + classNum +
                ", foodNum=" + foodNum +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'' +
                ", orderStatus=" + orderStatus +
                ", payStatus=" + payStatus +
                ", orderNo='" + orderNo + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                "} " + super.toString();
    }
}
