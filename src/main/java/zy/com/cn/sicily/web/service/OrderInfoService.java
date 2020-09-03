package zy.com.cn.sicily.web.service;

import com.github.pagehelper.PageInfo;
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
     * 根据订单号查询订单
     * @param orderNo
     * @return
     */
    OrderInfo getOrderByOrderNo(String orderNo);

    /**
     * 根据第三方（微信）充值订单号查询订单
     * @param outTradeNo
     * @return
     */
    OrderInfo getOrderByOutTradeNo(String outTradeNo);

    /**
     * 查询订单列表
     * @param info
     * @return
     */
    List<OrderInfo> listOrders(OrderInfo info);

    /**
     * 分页查询订单
     * @param pageNum
     * @param pageSize
     * @param info
     * @return
     */
    PageInfo<OrderInfo> pageOrders(Integer pageNum, Integer pageSize, OrderInfo info);
}
