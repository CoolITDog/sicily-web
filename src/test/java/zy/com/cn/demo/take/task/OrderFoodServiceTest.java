package zy.com.cn.demo.take.task;

import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import zy.com.cn.demo.take.task.model.OrderFood;
import zy.com.cn.demo.take.task.service.OrderFoodService;

import java.util.List;

/**
 * @title: OrderFoodServiceTest
 * @description: 订单食品服务接口测试用例
 * @author: zhangyan
 * @date: 2020-03-12 15:01
 * @version: 1.0
 **/
public class OrderFoodServiceTest extends DemoTakeTaskApplicationTests {
    @Autowired
    private OrderFoodService orderFoodService;

    @Test
    public void insertOrderFoodTest(){
        OrderFood foodOfOrder = new OrderFood();
        foodOfOrder.setAmount(2);
        foodOfOrder.setFoodId(2);
        foodOfOrder.setOrderId(1);
        OrderFood res = orderFoodService.insertOrderFood(foodOfOrder);
        System.out.println(res);
    }

    @Test
    public void listFoodsOfOrderTest(){
        OrderFood foodOfOrder = new OrderFood();
        foodOfOrder.setOrderId(1);
        List<OrderFood> res = orderFoodService.listFoodsOfOrder(foodOfOrder);
        System.out.println(res);
    }
}
