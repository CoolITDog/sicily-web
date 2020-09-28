package zy.com.cn.sicily.web.beans.vo;

import java.io.Serializable;

/**
 * @title: PaymentVO
 * @description: 支付请求对象
 * @author: zhangyan
 * @date: 2020-09-04 15:52
 * @version: 1.0
 **/
public class PaymentVO implements Serializable {

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 订单交易号
     */
    private String orderNo;

    /**
     * 支付金额
     */
    private String payPrice;
    /**
     * 商品描述
     */
    private String productName;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(String payPrice) {
        this.payPrice = payPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "PaymentVO{" +
                "orderId=" + orderId +
                ", orderNo='" + orderNo + '\'' +
                ", payPrice='" + payPrice + '\'' +
                '}';
    }
}
