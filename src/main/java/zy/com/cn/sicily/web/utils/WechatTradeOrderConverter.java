package zy.com.cn.sicily.web.utils;

import zy.com.cn.sicily.web.message.request.UnifiedOrderRequest;
import zy.com.cn.sicily.web.model.WechatTradeOrder;

/**
 * @Title: WechatTradeOrderConverter.java
 * @Description:
 * @version V1.0
 * @Company: didihu.com.cn
 * @Copyright Copyright (c) 2018
 */
public class WechatTradeOrderConverter {

	public static WechatTradeOrderConverter INSTANCE = new WechatTradeOrderConverter();
	
	public WechatTradeOrder converterToTarget(UnifiedOrderRequest source){
		if ( source == null ) {
            return null;
        }

        WechatTradeOrder wechatTradeOrder = new WechatTradeOrder();

        wechatTradeOrder.setGoodsDesc( source.getBody() );
        wechatTradeOrder.setAppId( source.getAppId() );
        wechatTradeOrder.setMerchantId( source.getMerchantId() );
        wechatTradeOrder.setSubAppId( source.getSubAppId() );
        wechatTradeOrder.setSubMerchantId( source.getSubMerchantId() );
        wechatTradeOrder.setOutTradeNo( source.getOutTradeNo() );
        wechatTradeOrder.setOpenId( source.getOpenId() );
        wechatTradeOrder.setSubOpenId( source.getSubOpenId() );
        wechatTradeOrder.setNonceStr( source.getNonceStr() );
        wechatTradeOrder.setAttach( source.getAttach() );
        wechatTradeOrder.setTotalFee( source.getTotalFee() );
        wechatTradeOrder.setTimeStart( source.getTimeStart() );
        wechatTradeOrder.setTimeExpire( source.getTimeExpire() );

        return wechatTradeOrder;
	}
}
