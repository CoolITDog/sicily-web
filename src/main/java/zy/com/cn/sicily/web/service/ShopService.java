package zy.com.cn.sicily.web.service;

import org.apache.commons.lang.StringUtils;
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
import zy.com.cn.sicily.web.beans.enums.PaymentErrorEnum;
import zy.com.cn.sicily.web.beans.enums.TradeStatusEnum;
import zy.com.cn.sicily.web.beans.vo.GenerateOrderVO;
import zy.com.cn.sicily.web.manager.WeChatPaymentManager;
import zy.com.cn.sicily.web.mapper.WechatTradeOrderMapper;
import zy.com.cn.sicily.web.message.request.UnifiedOrderRequest;
import zy.com.cn.sicily.web.message.response.UnifiedOrderResponse;
import zy.com.cn.sicily.web.model.Merchant;
import zy.com.cn.sicily.web.model.OrderInfo;
import zy.com.cn.sicily.web.model.WechatTradeOrder;
import zy.com.cn.sicily.web.utils.SignUtil;
import zy.com.cn.sicily.web.utils.UnifiedOrderConverter;
import zy.com.cn.sicily.web.utils.WechatTradeOrderConverter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private WechatTradeOrderMapper wechatTradeOrderMapper;
    /**
     * 微信支付下订单
     * @param generateOrderVO
     * @return
     */
    /*public OrderInfo invokeWxUnifiedOrder(GenerateOrderVO generateOrderVO, String ipAddr){
        Merchant merchant = merchantService.getMerchant();
        if (merchant != null) {
            CreateOrderRequestDTO createOrderRequestDTO = new CreateOrderRequestDTO();
            createOrderRequestDTO.setSubAppId(merchant.getAppId());
            createOrderRequestDTO.setOrderCode(generateOrderVO.getOrderNo());
            createOrderRequestDTO.setSubOpenId(generateOrderVO.getOpenId());
            createOrderRequestDTO.setGoodsDesc(generateOrderVO.getProductName());
            String attach = new StringBuilder()
                    .append(generateOrderVO.getUserId()).append(",")
                    .append(generateOrderVO.getOrderNo()).toString();
            logger.info("attach :{}", attach);
            String payPrice = generateOrderVO.getPrice().toString();
            Integer totalFee = 0;
            if (StringUtils.isNotEmpty(payPrice)) {
                BigDecimal bd = new BigDecimal(100);
                BigDecimal priceBd = new BigDecimal(payPrice).multiply(bd).setScale(2, RoundingMode.UP);
                totalFee = priceBd.intValue();
            }
            createOrderRequestDTO.setTotalFee(totalFee);
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = new Date();
            // 5分钟超时（网络延时，超时设置为6分钟）
            String timeStart = df.format(date);
            String timeExpire = df.format(new Date(date.getTime() + 360000));
            createOrderRequestDTO.setTimeStart(timeStart);
            createOrderRequestDTO.setTimeExpire(timeExpire);
            createOrderRequestDTO.setSpbillCreateIp(ipAddr);
            // 微信支付回调地址
            String notifyUrl = environment.getProperty("sicily.web.wx.pay.notify.url");
            createOrderRequestDTO.setNotifyUrl(notifyUrl);
            createOrderRequestDTO.setAttach(attach);
            RpcResponse<CreateOrderResponseDTO> createOrderResponse = wechatPaymentRemote.createOrder(createOrderRequestDTO);
            logger.info("wechatPaymentRemote.createOrder result:{}", createOrderResponse.getResult());
            //更新outTradeNo到订单数据库
            if (createOrderResponse.getResult() != null) {
                CreateOrderResponseDTO orderResponseDTO = createOrderResponse.getResult();
                WxShopActivityRechargeRecordDTO updateOutTradeNo = new WxShopActivityRechargeRecordDTO();
                updateOutTradeNo.setId(paymentOrderRequestVO.getOrderId());
                updateOutTradeNo.setOutTradeNo(orderResponseDTO.getOutTradeNo());
                updateOutTradeNo.setPhone(paymentOrderRequestVO.getPhone());
                updateOutTradeNo.setShopId(paymentOrderRequestVO.getActivityId());
                RpcResponse<ShopActivityRechargeRecordDTO> updateRes = wxShopRechargeRecordRemote.editShopInfoByID(updateOutTradeNo);
                logger.info("update outTradeNo result:{}", updateRes.getResult());
            }
            return ResultEntity.result(createOrderResponse);
        }
    }*/

    /**
     * 创建微信订单
     * @param createOrderRequestDTO
     * @return
     * @throws Exception
     */
    /*private CreateOrderResponseDTO createOrder(CreateOrderRequestDTO createOrderRequestDTO)throws Exception{
        UnifiedOrderRequest request = UnifiedOrderConverter.INSTANCE.converterToTarget(createOrderRequestDTO);
        request.setNonceStr(UUID.randomUUID().toString().replace("-", "").substring(0, 32));
        request.setOutTradeNo(String.valueOf(nextId()));
        UnifiedOrderResponse response = weChatPaymentManager.unifiedOrder(request);
        if(response.isFail()){
            throw new Exception(response.getErrMessage());
        }
        // 保存交易订单信息
        WechatTradeOrder wechatTradeOrder = saveOrder(request, createOrderRequest.getOrderCode());
        logger.info("save wechat trade order success:{}", wechatTradeOrder);
        String apiKey = environment.getProperty("api.key");
        // 服务商模式
        String appId = request.getSubAppId();
        WechatPaymentConfig config = wechatPaymentConfigService.getByAppId(request.getAppId());
        // 判断是否为普通商户模式
        if(config != null){
            apiKey = config.getPartnerKey();
            appId = config.getAppId();
        }
        CreateOrderResponseDTO createOrderResponse = new CreateOrderResponseDTO();
        createOrderResponse.setNonceStr(request.getNonceStr());
        createOrderResponse.setSignType(property.getSignType());
        createOrderResponse.setPrepayId(response.getPrepayId());
        createOrderResponse.setTimeStamp(String.valueOf(System.currentTimeMillis()));
        createOrderResponse.setPaySign(SignUtil.getPaySign(createOrderResponse, appId, apiKey));
        createOrderResponse.setOutTradeNo(request.getOutTradeNo());
        logger.info("create order response:{}", createOrderResponse);
        return createOrderResponse;
    }*/

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
            wechatTradeOrderMapper.insertRecord(wechatTradeOrder);
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
     * 获得下一个ID (该方法是线程安全的)
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();
        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }else { //时间戳改变，毫秒内序列重置
            sequence = 0L;
        }
        //上次生成ID的时间截
        lastTimestamp = timestamp;
        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - epoch) << timestampLeftShift)
                | (dataCenterId << dataCenterIdShift)
                | (workerId << workerIdShift)
                | sequence;
    }
    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}
