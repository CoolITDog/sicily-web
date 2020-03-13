package zy.com.cn.demo.take.task.beans.dto;

import zy.com.cn.demo.take.task.model.OrderFood;

import java.util.List;

/**
 * @title:
 * @description: 订单详情传输对象
 * @author: zhangyan
 * @date: 2020-03-12 17:05
 * @version: 1.0
 **/
public class OrderInfoDTO extends OrderFood {

    private static final long serialVersionUID = 1L;

    /**
     * 订单的食物列表
     */
    private List<OrderFood> foods;

    public List<OrderFood> getFoods() {
        return foods;
    }

    public void setFoods(List<OrderFood> foods) {
        this.foods = foods;
    }

    @Override
    public String toString() {
        return "OrderInfoDTO{" +
                "foods=" + foods +
                "} " + super.toString();
    }
}
