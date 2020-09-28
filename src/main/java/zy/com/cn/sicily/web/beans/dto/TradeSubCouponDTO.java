package zy.com.cn.sicily.web.beans.dto;

import java.io.Serializable;

/**
 * @Title: TradeSubCouponDTO.java
 * @Description: 交易订单优惠券
 * @version V1.0
 */
public class TradeSubCouponDTO implements Serializable{

	private static final long serialVersionUID = -5449111103769419038L;

	/**
	 * CASH--充值代金券
		NO_CASH---非充值代金券
		
		并且订单使用了免充值券后有返回（取值：CASH、NO_CASH）。$n为下标,从0开始编号，举例：coupon_type_0
		
		注意：只有下单时订单使用了优惠，回调通知才会返回券信息。
		下列情况可能导致订单不可以享受优惠
	 */
	private String couponType;
	/**
	 * 代金券ID,$n为下标，从0开始编号
		注意：只有下单时订单使用了优惠，回调通知才会返回券信息。
		下列情况可能导致订单不可以享受优惠
	 */
	private String couponId;
	/**
	 * 单个代金券支付金额,$n为下标，从0开始编号
	 */
	private Integer couponFee;

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public Integer getCouponFee() {
		return couponFee;
	}

	public void setCouponFee(Integer couponFee) {
		this.couponFee = couponFee;
	}

	@Override
	public String toString() {
		return "TradeSubCouponDTO [couponType=" + couponType + ", couponId="
				+ couponId + ", couponFee=" + couponFee + "]";
	}
	
}
