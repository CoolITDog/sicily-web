package zy.com.cn.sicily.web.service;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zy.com.cn.sicily.web.beans.ResultEntity;
import zy.com.cn.sicily.web.beans.dto.CreateOrderRequestDTO;
import zy.com.cn.sicily.web.beans.dto.CreateOrderResponseDTO;
import zy.com.cn.sicily.web.beans.dto.PayNotifyRequestDTO;
import zy.com.cn.sicily.web.beans.enums.PaymentErrorEnum;
import zy.com.cn.sicily.web.beans.enums.TradeStatusEnum;
import zy.com.cn.sicily.web.beans.vo.GenerateOrderVO;
import zy.com.cn.sicily.web.manager.WeChatMessageManager;
import zy.com.cn.sicily.web.manager.WeChatPaymentManager;
import zy.com.cn.sicily.web.manager.WechatAccessManager;
import zy.com.cn.sicily.web.mapper.WechatTradeOrderMapper;
import zy.com.cn.sicily.web.message.request.UnifiedOrderRequest;
import zy.com.cn.sicily.web.message.response.PayNotifyResponse;
import zy.com.cn.sicily.web.message.response.UnifiedOrderResponse;
import zy.com.cn.sicily.web.model.*;
import zy.com.cn.sicily.web.utils.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

/**
 * @title: ShopService
 * @description: 下单服务层
 * @author: zhangyan
 * @date: 2020-08-12 14:25
 * @version: 1.0
 **/
@Service
public class ShopService {

    private Logger logger = LoggerFactory.getLogger(ShopService.class);
    /** 上次生成ID的时间截 */
    private long lastTimestamp = -1L;
    /** 毫秒内序列(0~4095) */
    private long sequence = 0L;
    /** 时间节点，算法从2018/5/21 11:26:52这个时间点开始，可以保证69年不重复。*/
    private final long epoch = 1526873212000L;
    /** 序列在id中占的位数 */
    private final long sequenceBits = 12L;
    /** 机器id所占的位数 */
    private final long workerIdBits = 5L;
    /** 机器ID向左移12位 */
    private final long workerIdShift = sequenceBits;
    /** 数据中心标识id所占的位数 */
    private final long dataCenterIdBits = 5L;
    /** 数据标识中心id向左移17位(12+5) */
    private final long dataCenterIdShift = sequenceBits + workerIdBits;
    /** 时间截向左移22位(5+5+12) */
    private final long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;

    /** 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);
    /** 工作机器ID(0~31)*/
    private long workerId;
    /** 数据中心ID(0~31) */
    private long dataCenterId;


    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private Environment environment;
    @Autowired
    private WeChatPaymentManager weChatPaymentManager;
    @Autowired
    private WechatTradeOrderService wechatTradeOrderService;
    @Autowired
    private WechatMessageConfigService wechatMessageConfigService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private WechatAccessManager wechatAccessManager;
    @Autowired
    private WeChatMessageManager weChatMessageManager;
    @Autowired
    private HttpSession session;
    @Autowired
    private PayNotifyRequestConverter payNotifyRequestConverter;
    /**
     * 保存订单原始记录
     * @param unifiedOrderRequest
     * @param orderCode
     * @return
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public WechatTradeOrder saveOrder(UnifiedOrderRequest unifiedOrderRequest, String orderCode) throws Exception{
        try{
            WechatTradeOrder wechatTradeOrder = WechatTradeOrderConverter.INSTANCE.converterToTarget(unifiedOrderRequest);
            wechatTradeOrder.setOrderCode(orderCode);
            wechatTradeOrder.setTradeStatus(TradeStatusEnum.USERPAYING.getValue());
            wechatTradeOrder.setCreatedBy(StringUtils.defaultIfEmpty(wechatTradeOrder.getOpenId(), wechatTradeOrder.getSubOpenId()));
            wechatTradeOrder.setCreatedDate(new Date());
            wechatTradeOrder.setDeletedFlag("N");
            wechatTradeOrderService.insertRecord(wechatTradeOrder);
            return wechatTradeOrder;
        }catch(Exception e){
            throw new Exception(PaymentErrorEnum.DATABASE_ERROR.getValue(), e);
        }
    }

    /**
     * 生产订单号
     * @param userId
     * @return
     * @throws Exception
     */
    public synchronized String generateOrderNo(String userId)throws Exception {
        // 3位随机数
        String randomStr = createRandomStr();
        // 订单号
        SimpleDateFormat sfDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String strDate = sfDate.format(new Date());
        String orderNo = strDate + randomStr + userId;
        logger.info("生成的orderNo = " + orderNo);
        // 通过此订单号查数据库
        OrderInfo exist = orderInfoService.getOrderByOrderNo(orderNo);
        if (null != exist) {
            // 如果订单已存在,则递归再生成一个订单
            logger.info("生产了相同的订单号，再次递归生产----------------");
            return generateOrderNo(userId);
        }
        return orderNo;
    }

    /**
     * 生成3位随机数
     */
    private String createRandomStr() {
        double random = Math.random();
        double limit = 0.1;
        while (random < limit) {
            random = Math.random();
        }
        Integer sum = 3;
        for (int i = 0; i < sum; i++) {
            random *= 10;
        }
        return (int) random + "";
    }

    /**
     * 支付成功回调
     * @param requestBody
     * @return
     */
    public String notifyAfterPaid(String requestBody){
        logger.info("payment notify requestBody:{}", requestBody);
        OrderInfo info = new OrderInfo();
        try {
            PayNotifyRequestDTO payNotifyRequest = new PayNotifyRequestDTO();
            payNotifyRequest.setNotifyXml(requestBody);
            PayNotifyResponse response = payNotifyRequestConverter.convert(payNotifyRequest.getNotifyXml());
            if(response.isFail()){
                return null;
            }
            // TODO
            String apiKey = "";
            boolean sign = SignUtil.verify(payNotifyRequest.getNotifyXml(), apiKey, response.getSign());
            if(!sign){
                return null;
            }
            //支付成功，修改支付状态
            WechatTradeOrder tradeOrder = new WechatTradeOrder();
            tradeOrder.setOutTradeNo(response.getOutTradeNo());
            tradeOrder.setTradeStatus(TradeStatusEnum.SUCCESS.getValue());
            tradeOrder.setTradeSnapshot(payNotifyRequest.getNotifyXml());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            tradeOrder.setTradeTime(sdf.parse(response.getTimeEnd()));
            wechatTradeOrderService.updateRecordByOutTradeNo(tradeOrder);
            // 修改订单状态
            info = orderInfoService.getOrderByOutTradeNo(response.getOutTradeNo());
            info.setPayStatus(3);
            info.setOrderStatus(1);
            orderInfoService.updateOrderInfo(info);
        } catch (Exception e) {
            logger.error("notifyAfterPaid error:{}", e.getMessage(), e);
        }
        // 通知商店
        UserInfo condition = new UserInfo();
        condition.setId(info.getUserId());
        UserInfo user =  userInfoService.getOne(condition);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sendMessage(info.getId(), info.getPrice(), info.getRemark(),user.getWechatName(),sdf.format(info.getCreatedDate()));
        return "success";
    }

    /**
     * 发送消息
     * @param params
     */
    public void sendMessage(Object... params){
        Properties properties = new Properties();
        // 获取商家的openId
        Merchant merchant = merchantService.getMerchant();
        UserInfo condition = new UserInfo();
        condition.setMobile(merchant.getPhone());
        UserInfo userInfo = userInfoService.getOne(condition);
        properties.setProperty("touser", userInfo.getOpenId());
        // 获取模板内容
        WechatMessageConfig config = wechatMessageConfigService.getByMsgType(1);
        properties.setProperty("weapp_template_msg", config.getTemplate());
        String templateMessage = MessageFormat.format(PropertyConfig.getExtendString(properties,"weapp_template_msg"),params);
        String accessToken = wechatAccessManager.getAccessToken(merchant.getAppId(),merchant.getAppSecret());
        weChatMessageManager.uniformMessageSend(accessToken,templateMessage);
    }
}
