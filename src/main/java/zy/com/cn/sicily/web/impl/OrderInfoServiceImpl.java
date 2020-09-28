package zy.com.cn.sicily.web.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import zy.com.cn.sicily.web.mapper.OrderInfoMapper;
import zy.com.cn.sicily.web.model.OrderInfo;
import zy.com.cn.sicily.web.service.OrderInfoService;
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

    /**
     * 根据订单号查询订单
     *
     * @param orderNo
     * @return
     */
    @Override
    public OrderInfo getOrderByOrderNo(String orderNo) {
        Assert.notNull(orderNo, "orderNo is null");
        try{
            return orderInfoMapper.getOrderByOrderNo(orderNo);
        }catch (Exception e){
            logger.error("获取订单失败：{}", e.getMessage(), e);
            return null;
        }
    }

    /**
     * 根据第三方（微信）充值订单号查询订单
     *
     * @param outTradeNo
     * @return
     */
    @Override
    public OrderInfo getOrderByOutTradeNo(String outTradeNo) {
        Assert.notNull(outTradeNo, "outTradeNo is null");
        try{
            return orderInfoMapper.getOrderByOutTradeNo(outTradeNo);
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

    /**
     * 分页查询订单
     *
     * @param pageNum
     * @param pageSize
     * @param info
     * @return
     */
    @Override
    public PageInfo<OrderInfo> pageOrders(Integer pageNum, Integer pageSize, OrderInfo info) {
        try{
            PageHelper.startPage(pageNum, pageSize);
            List<OrderInfo> list = orderInfoMapper.listRecord(info);
            PageInfo<OrderInfo> pageInfo = new PageInfo<>(list, pageSize);
            return pageInfo;
        }catch (Exception e){
            logger.error("分页查询订单列表失败：{}", e.getMessage(), e);
            return null;
        }
    }
}
