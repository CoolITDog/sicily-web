package zy.com.cn.demo.take.task.service;

import zy.com.cn.demo.take.task.model.OrderFood;

import java.util.List;

/**
 * @title: OrderFoodService
 * @description: 订单食品映射服务层
 * @author: zhangyan
 * @date: 2020-03-12 14:55
 * @version: 1.0
 **/
public interface OrderFoodService {
    /**
     * 新增订单中的食物
     * @param foodOfOrder
     * @return
     */
    OrderFood insertOrderFood(OrderFood foodOfOrder);

    /**
     * 获取订单的食物列表
     * @param foodOfOrder
     * @return
     */
    List<OrderFood> listFoodsOfOrder(OrderFood foodOfOrder);
}
