package zy.com.cn.demo.take.task.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import zy.com.cn.demo.take.task.mapper.OrderInfoMapper;
import zy.com.cn.demo.take.task.model.OrderInfo;
import zy.com.cn.demo.take.task.service.OrderInfoService;

import java.util.List;

/**
 * @title: OrderInfoServiceImpl
 * @description: 订单信息服务实现
 * @author: zhangyan
 * @date: 2020-03-12 14:03
 * @version: 1.0
 **/
@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public OrderInfo insertOrderInfo(OrderInfo info) {
        Assert.notNull(info, "info is null");
        try{
            orderInfoMapper.insertRecord(info);
            return info;
        }catch (Exception e){
            logger.error("新增订单失败：{}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public OrderInfo updateOrderInfo(OrderInfo info) {
        Assert.notNull(info, "info is null");
        try{
            orderInfoMapper.updateRecord(info);
            return info;
        }catch (Exception e){
            logger.error("修改订单失败：{}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public OrderInfo getOrder(Integer id) {
        Assert.notNull(id, "id is null");
        try{
            return orderInfoMapper.getRecord(id);
        }catch (Exception e){
            logger.error("获取订单失败：{}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<OrderInfo> listOrders(OrderInfo info) {
        Assert.notNull(info, "info is null");
        try{
            return orderInfoMapper.listRecord(info);
        }catch (Exception e){
            logger.error("获取订单列表失败：{}", e.getMessage(), e);
            return null;
        }
    }
}
