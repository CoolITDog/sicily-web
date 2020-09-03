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
import zy.com.cn.sicily.web.beans.dto.OrderFoodDTO;
import zy.com.cn.sicily.web.beans.dto.OrderInfoDTO;
import zy.com.cn.sicily.web.cache.RedisCache;
import zy.com.cn.sicily.web.model.OrderFood;
import zy.com.cn.sicily.web.model.OrderInfo;
import zy.com.cn.sicily.web.service.OrderFoodService;
import zy.com.cn.sicily.web.service.OrderInfoService;
import zy.com.cn.sicily.web.utils.Constants;

import java.util.ArrayList;
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
    @Autowired
    private RedisCache redisCache;

    /**
     * 下订单
     * @param list 选择的食物列表
     * @param info 订单信息
     * @return orderInfo 订单信息
     */
    @PostMapping("add")
    @ResponseBody
    public ResultEntity<OrderInfo> addOrder(@RequestBody List<OrderFoodDTO> list, @RequestBody OrderInfo info){
        logger.info("addOrder param:{},{}", list, info);
        Assert.notNull(list, "list is null");
        Assert.notNull(info,"info is null");
        if(list.size() <= 0){
            return ResultEntity.error("食品列表为空");
        }
        try{
            List<OrderFoodDTO> okList = new ArrayList<OrderFoodDTO>();
            Double price = 0.0;
            Integer classNum = 0;
            Integer foodNum = 0;
            // 针对每一个商品判断是否可购买
            for(OrderFoodDTO dto:list){
                Integer remainNum = redisCache.descValueWithLua(Constants.SEC_KILL_NUMBER_KEY_PREFIX + dto.getFoodId(), dto.getAmount(), dto.getFoodId());
                if(remainNum < 0){
                    logger.info("抢购失败，foodId:{}, repository:{}, needAmount:{}", dto.getFoodId(), remainNum, dto.getAmount());
                }else{
                    price += dto.getPrice() * dto.getAmount();
                    classNum++;
                    foodNum += dto.getAmount();
                    okList.add(dto);
                }
            }
            info.setPrice(price);
            info.setClassNum(classNum);
            info.setFoodNum(foodNum);
            // 下单
            OrderInfo res = orderInfoService.insertOrderInfo(info);
            logger.info("下单结果：{}", res);
            // 保存食物列表
            for(OrderFoodDTO dto : okList){
                dto.setOrderId(res.getId());
                orderFoodService.insertOrderFood(dto);
            }
            return ResultEntity.success(res);
        }catch (Exception e){
            logger.error("orderInfoService.insertOrderInfo error:{}", e.getMessage(), e);
            return ResultEntity.error(e.getMessage());
        }
    }

    /**
     * 更改订单状态（取消、接单、）
     * @param info
     * @return
     */
    @PostMapping("update")
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
     * @param info
     * @return
     */
    @RequestMapping("/page")
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
    @GetMapping("get")
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
