package zy.com.cn.sicily.web.beans.dto;

import zy.com.cn.sicily.web.model.OrderFood;

/**
 * @title: OrderFoodDTO
 * @description: 订单食物传输对象
 * @author: zhangyan
 * @date: 2020-03-13 11:21
 * @version: 1.0
 **/
public class OrderFoodDTO extends OrderFood {

    private static final long serialVersionUID = 1L;

    /**
     * 食品单价
     */
    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderFoodDTO{" +
                "price=" + price +
                "} " + super.toString();
    }
}
