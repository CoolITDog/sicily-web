package zy.com.cn.sicily.web.controller.user;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import zy.com.cn.sicily.web.beans.ResultEntity;
import zy.com.cn.sicily.web.beans.dto.CreateOrderRequestDTO;
import zy.com.cn.sicily.web.beans.dto.CreateOrderResponseDTO;
import zy.com.cn.sicily.web.beans.enums.TradeStatusEnum;
import zy.com.cn.sicily.web.beans.vo.GenerateOrderVO;
import zy.com.cn.sicily.web.beans.vo.PaymentVO;
import zy.com.cn.sicily.web.cache.RedisCache;
import zy.com.cn.sicily.web.controller.BaseController;
import zy.com.cn.sicily.web.manager.WeChatPaymentManager;
import zy.com.cn.sicily.web.message.request.UnifiedOrderRequest;
import zy.com.cn.sicily.web.message.response.UnifiedOrderResponse;
import zy.com.cn.sicily.web.model.*;
import zy.com.cn.sicily.web.service.*;
import zy.com.cn.sicily.web.utils.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    private HttpServletRequest request;
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private OrderFoodService orderFoodService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private HttpSession session;
    @Autowired
    private WechatTradeOrderService wechatTradeOrderService;
    @Autowired
    private ISnowFlakeService snowFlakeService;
    @Autowired
    private WeChatPaymentManager weChatPaymentManager;

    private Integer timeOut = 300;

    private String signType = "MD5";

    /**
     * 下订单，生成本地订单
     * @param generateOrderVO 订单信息
     * @return orderInfo 订单信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/generateOrder")
    @ResponseBody
    public ResultEntity<OrderInfo> addOrder(@RequestBody GenerateOrderVO generateOrderVO){
        logger.info("addOrder param:{}", generateOrderVO);
        Assert.notNull(generateOrderVO, "vo is null");
        List<OrderFood> list = generateOrderVO.getFoodList();
        Assert.notNull(list, "food list is null");
        if(list.size() <= 0){
            return ResultEntity.error("食品列表为空");
        }
        try{
            List<OrderFood> okList = new ArrayList<OrderFood>();
            Double price = 0.0;
            Integer classNum = 0;
            Integer foodNum = 0;
            // 针对每一个商品判断是否可购买
            for(OrderFood dto:list){
                //是否有库存
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
            // 插入订单信息表
            OrderInfo info = new OrderInfo();
            BeanUtils.copyProperties(generateOrderVO,info);
            info.setOrderStatus(0);
            info.setPayStatus(1);
            info.setClassNum(classNum);
            info.setPrice(price);
            info.setFoodNum(foodNum);
            // 生成订单号
            String orderNo = shopService.generateOrderNo(generateOrderVO.getUserId().toString());
            info.setOrderNo(orderNo);
            // 下单
            OrderInfo res = orderInfoService.insertOrderInfo(info);
            logger.info("下单结果：{}", res);
            // 保存食物列表
            for(OrderFood dto : okList){
                dto.setOrderId(res.getId());
                orderFoodService.insertOrderFood(dto);
            }
            if(okList.size() < list.size()){
                return ResultEntity.success(res, "部分商品库存不足");
            }
            return ResultEntity.success(res);
        }catch (Exception e){
            logger.error("orderInfoService.insertOrderInfo error:{}", e.getMessage(), e);
            return ResultEntity.error(e.getMessage());
        }
    }

    /**
     * 调用微信支付
     * @param paymentVO
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "unifiedOrder")
    @ResponseBody
    public ResultEntity<CreateOrderResponseDTO> invokeWxUnifiedOrder(@RequestBody PaymentVO paymentVO){
        logger.info("addOrder param:{}", paymentVO);
        Assert.notNull(paymentVO, "vo is null");
        // 查找订单信息
        OrderInfo info = orderInfoService.getOrder(paymentVO.getOrderId());
        logger.info("orderInfoService.getOrder :{}", info);
        if(null == info){
            return ResultEntity.error("订单不存在");
        }
        // 校验支付时间
        Date date = new Date();
        if(timeOut < date.getTime()/1000 - info.getCreatedDate().getTime()/1000){
            return ResultEntity.error("订单超时");
        }
        //TODO
//        UserInfo userInfo = (UserInfo) session.getAttribute(Constants.SESSION_USERINFO_KEY);
        UserInfo userInfo = new UserInfo();
        userInfo.setId(18);
        userInfo.setMobile("18373184605");
        userInfo.setWechatName("阿丙");
        userInfo.setOpenId("oE6v_47j5_delX7iugtMNw0EiI78");
        userInfo.setAvatarUrl("https://thirdwx.qlogo.cn/mmopen/vi_32/Vnx2INt7VaCzweJaFw35EJtZBYv3K0rKRZa3ffibEhn060fYolAVzWrcMN0paaML8oDnRGqbIuNiaHcsa1wHgJ7g/132");
        logger.info("userInfo:{}", userInfo);
        CreateOrderRequestDTO tradeOrder = new CreateOrderRequestDTO();
        // TODO
//        tradeOrder.setSubAppId(session.getAttribute(Constants.WE_CHAT_ID).toString());
        tradeOrder.setSubAppId("wxbf9c2f4f093a13bc");
        tradeOrder.setOrderCode(paymentVO.getOrderNo());
        tradeOrder.setSubOpenId(userInfo.getOpenId());
        tradeOrder.setGoodsDesc(paymentVO.getProductName());
        String attach = new StringBuilder()
                .append(userInfo.getOpenId()).append(",")
                .append(userInfo.getMobile()).append(",")
                .append(paymentVO.getOrderId()).toString();
        logger.info("attach :{}", attach);
        String payPrice = paymentVO.getPayPrice();
        Integer totalFee = 0;
        if (StringUtils.isNotEmpty(payPrice)) {
            BigDecimal bd = new BigDecimal(100);
            BigDecimal priceBd = new BigDecimal(payPrice).multiply(bd).setScale(2, RoundingMode.UP);
            totalFee = priceBd.intValue();
        }
        tradeOrder.setTotalFee(totalFee);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        tradeOrder.setTimeStart(sdf.format(date));
        tradeOrder.setTimeExpire(sdf.format(date.getTime()/1000 + timeOut));
        String notifyUrl = "http://192.168.3.82:8080/sicily-web/shop/notify";
        tradeOrder.setAttach(attach);
        UnifiedOrderRequest request = UnifiedOrderConverter.INSTANCE.converterToTarget(tradeOrder);
        request.setNonceStr(UUID.randomUUID().toString().replace("-", "").substring(0, 32));
        request.setOutTradeNo(String.valueOf(snowFlakeService.getSnowFlakeID()));
        request.setNotifyUrl(notifyUrl);
        UnifiedOrderResponse response = weChatPaymentManager.unifiedOrder(request);
        if(response.isFail()){
            return ResultEntity.error("生成订单失败");
        }
        // 保存交易订单信息
        try{
            WechatTradeOrder wechatTradeOrder = WechatTradeOrderConverter.INSTANCE.converterToTarget(request);
            wechatTradeOrder.setOrderCode(paymentVO.getOrderNo());
            wechatTradeOrder.setTradeStatus(TradeStatusEnum.USERPAYING.getValue());
            wechatTradeOrder.setCreatedBy(StringUtils.defaultIfEmpty(wechatTradeOrder.getOpenId(), wechatTradeOrder.getSubOpenId()));
            wechatTradeOrder.setCreatedDate(new Date());
            wechatTradeOrder.setDeletedFlag("N");
            wechatTradeOrderService.insertRecord(wechatTradeOrder);
            logger.info("交易订单信息：{}", wechatTradeOrder);
        }catch(Exception e){
            return ResultEntity.error("微信生成订单失败");
        }
        CreateOrderResponseDTO vo = new CreateOrderResponseDTO();
        vo.setNonceStr(request.getNonceStr());
        vo.setSignType(signType);
        vo.setPrepayId(response.getPrepayId());
        vo.setTimeStamp(String.valueOf(System.currentTimeMillis()));
        String appId = session.getAttribute(Constants.WE_CHAT_ID).toString();
        String apiKey = "";
        vo.setPaySign(SignUtil.getPaySign(vo, appId, apiKey));
        vo.setOutTradeNo(request.getOutTradeNo());
        logger.info("create order response:{}", vo);
        // 更新订单的交易订单号
        info.setOutTradeNo(request.getOutTradeNo());
        orderInfoService.updateOrderInfo(info);
        return ResultEntity.success(vo);
    }

    /**
     * 支付成功异步回调
     * @author yangjia
     * @date 2019/05/06
     */
    @RequestMapping(value = "notify")
    public String notifyAfterPaid() {
        return shopService.notifyAfterPaid(getRequestBody());
    }

    /**
     * 获取请求体内容
     * @return
     * @author Alvin
     * @date 2019年5月4日 下午3:31:07
     */
    protected String getRequestBody(){
        try {
            return IOUtils.toString(request.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
