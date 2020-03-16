package zy.com.cn.sicily.web.service;

import zy.com.cn.sicily.web.model.OrderInfo;

import java.util.List;

/**
 * @title: OrderInfoService
 * @description: 订单信息服务层
 * @author: zhangyan
 * @date: 2020-03-12 14:01
 * @version: 1.0
 **/
public interface OrderInfoService {

    /**
     * 新增订单
     * @param info
     * @return
     */
    OrderInfo insertOrderInfo(OrderInfo info);

    /**
     * 修改订单
     * @param info
     * @return
     */
    OrderInfo updateOrderInfo(OrderInfo info);

    /**
     * 根据主键查询订单
     * @param id
     * @return
     */
    OrderInfo getOrder(Integer id);

    /**
     * 查询订单列表
     * @param info
     * @return
     */
    List<OrderInfo> listOrders(OrderInfo info);
}
