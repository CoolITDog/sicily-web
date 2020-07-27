package zy.com.cn.sicily.web.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zy.com.cn.sicily.web.beans.enums.TradeCodeEnum;
import zy.com.cn.sicily.web.config.WeChatRequestUrl;
import zy.com.cn.sicily.web.http.HttpClient;
import zy.com.cn.sicily.web.message.request.UnifiedOrderRequest;
import zy.com.cn.sicily.web.message.response.UnifiedOrderResponse;
import zy.com.cn.sicily.web.model.Merchant;
import zy.com.cn.sicily.web.service.MerchantService;
import zy.com.cn.sicily.web.service.WeChatConfigService;
import zy.com.cn.sicily.web.utils.MessageUtil;
import zy.com.cn.sicily.web.utils.SignUtil;

/**
 * @title: WeChatPaymentManager
 * @description: 微信支付接口
 * @author: zhangyan
 * @date: 2020-07-24 09:30
 * @version: 1.0
 **/

@Component
public class WeChatPaymentManager {

    /**
     * 签名类型，目前支持默认为MD5
     */
    private String signType = "MD5";

    /**
     * 交易类型，小程序取值如下：JSAPI
     */
    private String tradeType = "JSAPI";

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MerchantService merchantService;
    @Autowired
    private WeChatConfigService weChatConfigService;
    /**
     * 统一下单（只支持普通商户模式）
     * @param request
     * @return
     */
    public UnifiedOrderResponse unifiedOrder(UnifiedOrderRequest request){
        logger.info("unifiedOrder:{}", request);
        UnifiedOrderResponse response = new UnifiedOrderResponse();
        try {
            Merchant merchant = merchantService.getMerchant();
            String apiKey = weChatConfigService.getByName("apiKey").getConfigContent();
            request.setAppId(merchant.getAppId());
            request.setMerchantId(merchant.getMerchantId().toString());
            request.setOpenId(request.getSubOpenId());
            request.setSubOpenId(null);
            request.setSubAppId(null);
            request.setSubMerchantId(null);
            request.setSignType(signType);
            request.setTradeType(tradeType);
            logger.info("unified order request:{}", request);
            request.setSign(SignUtil.getSign(request, apiKey));
            String entity = MessageUtil.beanToXml(request, UnifiedOrderRequest.class);
            logger.info("unified order request xml:{}", entity);
            String xmlResponse = HttpClient.doPost(WeChatRequestUrl.UNIFIED_ORDER, entity);
            logger.info("unified order response:{}", xmlResponse);
            response = MessageUtil.xmlToBean(xmlResponse, UnifiedOrderResponse.class);
            if(response.isFail()){
                return response;
            }
            boolean sign = SignUtil.verify(response, apiKey, response.getSign());
            if(!sign){
                response.setReturnCode(TradeCodeEnum.FAIL.getValue());
                response.setReturnMsg("sign verify fail");
            }
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setReturnCode(TradeCodeEnum.FAIL.getValue());
            response.setReturnMsg(e.getMessage());
        }
        return response;
    }
}
