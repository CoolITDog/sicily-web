package zy.com.cn.sicily.web.controller.user;

import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import zy.com.cn.sicily.web.beans.ResultEntity;
import zy.com.cn.sicily.web.beans.dto.OrderInfoDTO;
import zy.com.cn.sicily.web.model.OrderFood;
import zy.com.cn.sicily.web.model.OrderInfo;
import zy.com.cn.sicily.web.service.OrderFoodService;
import zy.com.cn.sicily.web.service.OrderInfoService;

import java.util.List;

/**
 * @Title OrderController
 * @description 订单操作控制层
 * @author zhangyan
 * @date 2020-03-13 11:05
 * @version 1.0
 **/
@Controller
@RequestMapping("personal")
public class PersonalController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private OrderFoodService orderFoodService;


    /**
     * 更改订单状态（取消、接单、）
     * @param info
     * @return
     */
    @PostMapping("order/update")
    @ResponseBody
    public ResultEntity<OrderInfo> updateOrder(@RequestBody OrderInfo info){
        logger.info("cancelOrder param:{}", info);
        Assert.notNull(info, "info is null");
        Assert.notNull(info.getId(), "orderId is null");
        try{
            OrderInfo res = orderInfoService.updateOrderInfo(info);
            return ResultEntity.success(res);
        }catch (Exception e){
            logger.error("orderInfoService.updateOrderInfo error:{}", e.getMessage(), e);
            return ResultEntity.error(e.getMessage());
        }
    }

    /**
     * 分页查找订单列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/order/page")
    public ResultEntity<PageInfo<OrderInfo>> pageOrders(Integer pageNum, Integer pageSize, Integer userId){
        try{
            OrderInfo info = new OrderInfo();
            info.setUserId(userId);
            PageInfo<OrderInfo> res = orderInfoService.pageOrders(pageNum, pageSize, info);
            return ResultEntity.success(res);
        }catch (Exception e){
            logger.error("pageOrders error:{}", e.getMessage(), e);
            return ResultEntity.error(e.getMessage());
        }
    }

    /**
     * 获取订单详情
     * @param orderId
     * @return
     */
    @GetMapping("order/get")
    @ResponseBody
    public ResultEntity<OrderInfoDTO> getDetail(Integer orderId){
        logger.info("getDetail params:{}", orderId);
        try{
            OrderInfoDTO order = new OrderInfoDTO();
            // 获取订单基础信息
            OrderInfo info = orderInfoService.getOrder(orderId);
            logger.info("orderInfoService.getOrder :{}", info);
            if(null == info){
                return ResultEntity.error("订单信息为空");
            }
            BeanUtils.copyProperties(info, order);
            OrderFood food = new OrderFood();
            food.setOrderId(info.getId());
            List<OrderFood> foods = orderFoodService.listFoodsOfOrder(food);
            logger.info("orderFoodService.listFoodsOfOrder :{}", foods);
            order.setFoods(foods);
            return ResultEntity.success(order);
        }catch (Exception e){
            logger.error("orderInfoService.getOrder error:{}", e.getMessage(), e);
            return ResultEntity.error(e.getMessage());
        }
    }

}
