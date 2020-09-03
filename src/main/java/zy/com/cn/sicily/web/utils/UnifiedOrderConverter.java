package zy.com.cn.sicily.web.utils;

import zy.com.cn.sicily.web.beans.dto.CreateOrderRequestDTO;
import zy.com.cn.sicily.web.message.request.UnifiedOrderRequest;

/**
 * @Title: UnifiedOrderConverter.java
 * @Description:
 * @version V1.0
 * @Company: didihu.com.cn
 * @Copyright Copyright (c) 2018
 */
public class UnifiedOrderConverter {

	public static UnifiedOrderConverter INSTANCE = new UnifiedOrderConverter();
	
	public UnifiedOrderRequest converterToTarget(CreateOrderRequestDTO source){
		if ( source == null ) {
            return null;
        }

        UnifiedOrderRequest unifiedOrderRequest = new UnifiedOrderRequest();

        unifiedOrderRequest.setBody( source.getGoodsDesc() );
        unifiedOrderRequest.setSubAppId( source.getSubAppId() );
        unifiedOrderRequest.setSubMerchantId( source.getSubMerchantId() );
        unifiedOrderRequest.setAttach( source.getAttach() );
        if ( source.getTotalFee() != null ) {
            unifiedOrderRequest.setTotalFee( source.getTotalFee() );
        }
        unifiedOrderRequest.setTimeStart( source.getTimeStart() );
        unifiedOrderRequest.setTimeExpire( source.getTimeExpire() );
        unifiedOrderRequest.setNotifyUrl( source.getNotifyUrl() );
        unifiedOrderRequest.setSubOpenId( source.getSubOpenId() );
        unifiedOrderRequest.setSpbillCreateIp( source.getSpbillCreateIp() );

        return unifiedOrderRequest;
	}
}
