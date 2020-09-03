package zy.com.cn.sicily.web.controller.merchant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zy.com.cn.sicily.web.beans.ResultEntity;
import zy.com.cn.sicily.web.cache.RedisCache;
import zy.com.cn.sicily.web.model.*;
import zy.com.cn.sicily.web.service.*;
import zy.com.cn.sicily.web.utils.RedisUtil;

import javax.annotation.Resource;
import java.util.List;

import static zy.com.cn.sicily.web.utils.Constants.CATEGORY_REDIS_PREFIX;

/**
 * @title: MerchantController
 * @description: 商户操作控制器
 * @author: zhangyan
 * @date: 2020-03-12 16:50
 * @version: 1.0
 **/
@Deprecated
@Controller
@RequestMapping("merchant")
public class MerchantController {

    @Autowired
    private FoodCategoryService foodCategoryService;
    @Autowired
    private FoodInfoService foodInfoService;
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private OrderFoodService orderFoodService;
    @Autowired
    private MerchantService merchantService;
    @Resource
    private RedisUtil redisUtil;
    @Autowired
    private RedisCache redisCache;
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 新增菜单项
     * @param category
     * @return
     */
    @PostMapping("addCategory")
    @ResponseBody
    public ResultEntity<FoodCategory> addCategory(FoodCategory category){
        logger.info("addCategory param:{}", category);
        try{
            FoodCategory res = foodCategoryService.insertCategory(category);
            logger.info("foodCategoryService.insertCategory {}", res);
            // 删除缓存
            redisUtil.del(CATEGORY_REDIS_PREFIX);
            return ResultEntity.success(res);
        }catch (Exception e){
            logger.error("foodCategoryService.insertCategory error:{}", e.getMessage(), e);
            return ResultEntity.error(e.getMessage());
        }
    }

    /**
     * 新增食品
     * @param info
     * @return
     */
    @PostMapping("addFoodInfo")
    @ResponseBody
    public ResultEntity<FoodInfo> addFoodInfo(FoodInfo info){
        logger.info("addFoodInfo param:{}", info);
        try {
            FoodInfo res = foodInfoService.insertFoodInfo(info);
            logger.info("foodInfoService.insertFoodInfo :{}", res);
            // 设置库存缓存
            redisCache.setSecKillNum(res.getId(), res.getTodayRepository());
            return ResultEntity.success(res);
        }catch (Exception e){
            logger.error("foodInfoService.insertFoodInfo error:{}", e.getMessage(), e);
            return ResultEntity.error(e.getMessage());
        }
    }

    /**
     * 获取订单列表
     * @param orderInfo
     * @return
     */
    @GetMapping("listOrders")
    @ResponseBody
    public ResultEntity<List<OrderInfo>> listOrders(OrderInfo orderInfo){
        logger.info("listOrders param:{}", orderInfo);
        try {
            List<OrderInfo> res = orderInfoService.listOrders(orderInfo);
            logger.info("orderInfoService.listOrders :{}", res);
            return ResultEntity.success(res);
        }catch (Exception e){
            logger.error("orderInfoService.listOrders error:{}", e.getMessage(), e);
            return ResultEntity.error(e.getMessage());
        }
    }

    /**
     * （废弃：使用OrderController.getDetail()方法代替）
     * 获取订单的食物列表
     * @param orderId
     * @return
     */
    @GetMapping("listFoodsByOrderId")
    @ResponseBody
    public ResultEntity<List<OrderFood>> listFoodsByOrderId(Integer orderId){
        logger.info("listFoodsByOrderId param:{}", orderId);
        if(null == orderId){
            return ResultEntity.error("订单id为空");
        }
        try {
            OrderFood condition = new OrderFood();
            condition.setOrderId(orderId);
            List<OrderFood> list = orderFoodService.listFoodsOfOrder(condition);
            logger.info("orderFoodService.listFoodsOfOrder :{}", list);
            return ResultEntity.success(list);
        }catch (Exception e){
            logger.error("orderFoodService.listFoodsOfOrder error:{}", e.getMessage(), e);
            return ResultEntity.error(e.getMessage());
        }
    }

    /**
     * 校验店家身份
     * @param merchant
     * @return
     */
    @RequestMapping("verify")
    @ResponseBody
    public ResultEntity<String> merchantVerify(Merchant merchant){
        logger.info("merchantVerify param:{}", merchant);
        Assert.notNull(merchant,"merchant is null");
        try{
            Merchant merchantInfo = merchantService.getMerchant();
            if(merchantInfo.getPassword().equals(merchant.getPassword())){
                return ResultEntity.success("success");
            }
        }catch (Exception e){
            logger.error("merchantService.getMerchant error:{}", e.getMessage(), e);
        }
        return ResultEntity.error("failure");
    }
}
