package zy.com.cn.sicily.web.controller.merchant;

import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import zy.com.cn.sicily.web.beans.ResultEntity;
import zy.com.cn.sicily.web.model.OrderInfo;
import zy.com.cn.sicily.web.service.OrderInfoService;

/**
 * @title: OrderController
 * @description: 订单管理
 * @author: zhangyan
 * @date: 2020-08-11 09:36
 * @version: 1.0
 **/
@Controller
@RequestMapping("/order")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderInfoService orderInfoService;

    @RequestMapping("/index")
    public String order(){
        return "order";
    }

    /**
     * 分页查找订单列表
     * @param pageNum
     * @param pageSize
     * @param info
     * @return
     */
    @RequestMapping("/page")
    public ResultEntity<PageInfo<OrderInfo>> pageOrders(Integer pageNum, Integer pageSize, OrderInfo info){
        try{
            PageInfo<OrderInfo> res = orderInfoService.pageOrders(pageNum, pageSize, info);
            return ResultEntity.success(res);
        }catch (Exception e){
            logger.error("pageOrders error:{}", e.getMessage(), e);
            return ResultEntity.error(e.getMessage());
        }
    }
}
