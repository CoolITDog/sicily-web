package zy.com.cn.sicily.web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import zy.com.cn.sicily.web.mapper.OrderFoodMapper;
import zy.com.cn.sicily.web.model.OrderFood;
import zy.com.cn.sicily.web.service.OrderFoodService;

import java.util.List;

/**
 * @title: OrderFoodServiceImpl
 * @description: 订单食品映射服务实现
 * @author: zhangyan
 * @date: 2020-03-12 14:57
 * @version: 1.0
 **/
@Service
public class OrderFoodServiceImpl implements OrderFoodService {

    @Autowired
    private OrderFoodMapper orderFoodMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public OrderFood insertOrderFood(OrderFood foodOfOrder) {
        Assert.notNull(foodOfOrder, "foddOrder is null");
        try {
            orderFoodMapper.insertRecord(foodOfOrder);
            return foodOfOrder;
        }catch (Exception e){
            logger.error("新增订单食品失败：{}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<OrderFood> listFoodsOfOrder(OrderFood foodOfOrder) {
        Assert.notNull(foodOfOrder, "foodOfOrder is null");
        try {
            return orderFoodMapper.listRecords(foodOfOrder);
        }catch (Exception e){
            logger.error("查询订单食品列表失败：{}", e.getMessage(), e);
            return null;
        }
    }
}
