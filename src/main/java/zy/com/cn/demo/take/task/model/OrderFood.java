package zy.com.cn.demo.take.task.model;

import javax.persistence.Table;

/**
 * @title: OrderFood
 * @description: 订单食物映射实体对象
 * @author: zhangyan
 * @date: 2020-03-12 14:42
 * @version: 1.0
 **/
@Table(name = "sicily_order_food")
public class OrderFood extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 食物id
     */
    private Integer foodId;

    /**
     * 数量
     */
    private Integer amount;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OrderFood{" +
                "orderId=" + orderId +
                ", foodId=" + foodId +
                ", amount=" + amount +
                "} " + super.toString();
    }
}
