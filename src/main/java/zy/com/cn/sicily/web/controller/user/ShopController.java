package zy.com.cn.sicily.web.controller.user;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zy.com.cn.sicily.web.beans.ResultEntity;
import zy.com.cn.sicily.web.beans.dto.CreateOrderRequestDTO;
import zy.com.cn.sicily.web.beans.vo.CreateOrderResponseVO;
import zy.com.cn.sicily.web.beans.vo.GenerateOrderVO;
import zy.com.cn.sicily.web.controller.BaseController;
import zy.com.cn.sicily.web.model.Merchant;
import zy.com.cn.sicily.web.model.OrderFood;
import zy.com.cn.sicily.web.model.OrderInfo;
import zy.com.cn.sicily.web.service.MerchantService;
import zy.com.cn.sicily.web.service.OrderFoodService;
import zy.com.cn.sicily.web.service.OrderInfoService;
import zy.com.cn.sicily.web.service.ShopService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

/**
 * @title: ShopController
 * @description: 商品下单
 * @author: zhangyan
 * @date: 2020-08-12 10:44
 * @version: 1.0
 **/
@Controller
@RequestMapping("/shop")
public class ShopController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ShopController.class);

    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private OrderFoodService orderFoodService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private ShopService shopService;
    /**
     * 生成订单
     * @param generateOrderVO
     * @return
     */
    @RequestMapping("/generateOrder")
    @ResponseBody
    public ResultEntity<OrderInfo> generateOrder(@RequestBody GenerateOrderVO generateOrderVO){
        try{
            // 生成订单号
            String orderNo = shopService.generateOrderNo(generateOrderVO.getUserId().toString());
            // 插入订单信息表
            OrderInfo info = new OrderInfo();
            BeanUtils.copyProperties(generateOrderVO,info);
            info.setOrderStatus(0);
            info.setPayStatus(1);
            info.setClassNum(generateOrderVO.getFoodList().size());
            info.setOrderNo(orderNo);
            OrderInfo order = orderInfoService.insertOrderInfo(info);
            logger.info("generateOrder order:{}", order);
            if(null != order){
                // 插入订单对应食品信息
                for (OrderFood food:generateOrderVO.getFoodList()){
                    food.setOrderId(order.getId());
                    orderFoodService.insertOrderFood(food);
                    logger.info("generateOrder food:{}", food);
                }
            }
            return ResultEntity.success(order);
        }catch (Exception e){
            logger.error("generateOrder error:{}", e.getMessage(), e);
            return ResultEntity.error(e.getMessage());
        }
    }

   /* @RequestMapping("/unifiedOrder")
    public ResultEntity<CreateOrderResponseVO>  invokeWxUnifiedOrder(GenerateOrderVO generateOrderVO){
        logger.info("invokeWxUnifiedOrder generateOrderVO:{}", generateOrderVO);
        //根据订单id查看订单状态，若状态异常则下单失败
        OrderInfo info = orderInfoService.getOrder(generateOrderVO.getOrderId().intValue());
        logger.info("invokeWxUnifiedOrder getOrder:{}", info);
        if(info == null){
            return ResultEntity.error(null,"订单不存在");
        }
        Date date = new Date();
        Date createdDate = info.getCreatedDate();
        // 5分钟超时
        if(date.getTime()/1000 - createdDate.getTime()/1000 > 300){
            return ResultEntity.error(null,"订单超时");
        }
        Merchant merchant = merchantService.getMerchant();

    }*/
}
