package zy.com.cn.demo.take.task.model;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "userId=" + userId +
                ", attainWay='" + attainWay + '\'' +
                ", price=" + price +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                "} " + super.toString();
    }
}
