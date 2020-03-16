package zy.com.cn.sicily.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import zy.com.cn.sicily.web.model.OrderInfo;
import zy.com.cn.sicily.web.service.OrderInfoService;

import java.util.List;

/**
 * @title: OrderInfoServiceTest
 * @description: 订单信息接口测试用例
 * @author: zhangyan
 * @date: 2020-03-12 14:08
 * @version: 1.0
 **/
public class OrderInfoServiceTest extends DemoTakeTaskApplicationTests {

    @Autowired
    private OrderInfoService orderInfoService;

    @Test
    public void insertOrderInfoTest(){
        OrderInfo info = new OrderInfo();
        info.setUserId(2);
        info.setAddress("adsadas");
        info.setAttainWay(1);
        info.setPrice(18.05);
        info.setStatus(1);
        info.setCreatedBy("we");
        OrderInfo res = orderInfoService.insertOrderInfo(info);
        System.out.println(res);
    }

    @Test
    public void updateOrderInfoTest(){
        OrderInfo info = new OrderInfo();
        info.setId(1);
        info.setStatus(2);
        OrderInfo res = orderInfoService.updateOrderInfo(info);
        System.out.println(res);
    }
    @Test
    public void getOrderTest(){
        OrderInfo res = orderInfoService.getOrder(1);
        System.out.println(res);
    }

    @Test
    public void listOrdersTest(){
        OrderInfo info = new OrderInfo();
        List<OrderInfo> res = orderInfoService.listOrders(info);
        System.out.println(res);
    }
}
