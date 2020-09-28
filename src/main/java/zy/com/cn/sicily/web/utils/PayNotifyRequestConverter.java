package zy.com.cn.sicily.web.utils;


import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import zy.com.cn.sicily.web.beans.dto.TradeSubCouponDTO;
import zy.com.cn.sicily.web.message.response.PayNotifyResponse;

import java.util.ArrayList;
import java.util.List;


/**
 * @Title: PayNotifyRequestConverter.java
 * @Description:
 * @author Alvin  
 * @date 2020年6月17日 上午11:35:00
 * @version V1.0  
 * @Company: didihu.com.cn
 * @Copyright Copyright (c) 2020
 */
@Component
public class PayNotifyRequestConverter extends AbstractXmlConverter<PayNotifyResponse> {

	@Override
	public PayNotifyResponse unmarshal(PayNotifyResponse request, java.util.Map<String,String> xmlData) {
		Integer couponCount = request.getCouponCount();
		if(couponCount == null){
			return request;
		}
		List<TradeSubCouponDTO> couponList = new ArrayList<TradeSubCouponDTO>();
		TradeSubCouponDTO coupon = null;
		for(int i = 0; i< couponCount; i++){
			coupon = new TradeSubCouponDTO();
			coupon.setCouponType(xmlData.get("coupon_type_".concat(String.valueOf(i))));
			coupon.setCouponId(xmlData.get("coupon_id_".concat(String.valueOf(i))));
			String couponFee = xmlData.get("coupon_fee_".concat(String.valueOf(i)));
			if(StringUtils.isNotEmpty(couponFee)){
				coupon.setCouponFee(Integer.valueOf(couponFee));
			}
			couponList.add(coupon);
		}
		return request;
	}
}
