package zy.com.cn.sicily.web.message.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import zy.com.cn.sicily.web.beans.dto.TradeSubCouponDTO;

import java.util.List;

/**
 * @Title: PayNotifyRequest.java
 * @Description: 支付回调通知请求
 * @version V1.0
 */
@XStreamAlias("xml")
public class PayNotifyResponse extends BaseResponse {

	private static final long serialVersionUID = -2567201568620308911L;

	// 以下字段在return_code 和result_code都为SUCCESS的时候有返回

	/**
	 * 签名类型,目前支持HMAC-SHA256和MD5，默认为MD5
	 */
	@XStreamAlias("sign_type")
	private String signType;

	/**
	 * 调用接口提交的终端设备号
	 */
	@XStreamAlias("device_info")
	private String deviceInfo;

	/**
	 * 用户标识:trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识
	 */
	@XStreamAlias("openid")
	private String openId;

	/**
	 * 用户是否关注公众账号，Y-关注，N-未关注
	 */
	@XStreamAlias("is_subscribe")
	private String isSubscribe;

	/**
	 * trade_type=JSAPI，此参数必传，用户在子商户appid下的唯一标识。openid和sub_openid可以选传其中之一，
	 * 如果选择传sub_openid,则必须传sub_appid
	 */
	@XStreamAlias("sub_openid")
	private String subOpenId;

	/**
	 * 用户是否关注子公众账号，Y-关注，N-未关注
	 */
	@XStreamAlias("sub_is_subscribe")
	private String subIsSubscribe;

	/**
	 * 交易类型:取值如下：JSAPI，NATIVE，APP，WAP
	 */
	@XStreamAlias("trade_type")
	private String tradeType;

	/**
	 * SUCCESS—支付成功
	 * 
	 * REFUND—转入退款
	 * 
	 * NOTPAY—未支付
	 * 
	 * CLOSED—已关闭
	 * 
	 * REVOKED—已撤销（刷卡支付）
	 * 
	 * USERPAYING--用户支付中
	 * 
	 * PAYERROR--支付失败(其他原因，如银行返回失败)
	 */
	@XStreamAlias("trade_state")
	private String tradeState;

	/**
	 * 银行类型，采用字符串类型的银行标识
	 */
	@XStreamAlias("bank_type")
	private String bankType;

	/**
	 * 订单总金额，单位为分
	 */
	@XStreamAlias("total_fee")
	private Integer totalFee;

	/**
	 * 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY
	 */
	@XStreamAlias("fee_type")
	private String feeType;

	/**
	 * 现金支付金额订单现金支付金额
	 */
	@XStreamAlias("cash_fee")
	private Integer cashFee;

	/**
	 * 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY
	 */
	@XStreamAlias("cash_fee_type")
	private String cashFeeType;
	
	/**
	 * 当订单使用了免充值型优惠券后返回该参数，应结订单金额=订单金额-免充值优惠券金额
	 */
	@XStreamAlias("settlement_total_fee")
	private Integer settlementTotalFee;
	
	/**
	 * 代金券金额
	 */
	@XStreamAlias("coupon_fee")
	private Integer couponFee;
	
	/**
	 * 代金券使用数量
	 */
	@XStreamAlias("coupon_count")
	private Integer couponCount;

	/**
	 * 代金券类型
	 */
	@XStreamAlias("coupon_type")
	private Integer couponType;
	
	/**
	 * 代金券ID
	 */
	@XStreamAlias("coupon_id")
	private String couponId;
	
	/**
	 * 微信支付订单号
	 */
	@XStreamAlias("transaction_id")
	private String transactionId;

	/**
	 * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一
	 */
	@XStreamAlias("out_trade_no")
	private String outTradeNo;

	/**
	 * 商家数据包
	 */
	@XStreamAlias("attach")
	private String attach;

	/**
	 * 订单支付时间，格式为yyyyMMddHHmmss
	 */
	@XStreamAlias("time_end")
	private String timeEnd;

	/**
	 * 对当前查询订单状态的描述和下一步操作的指引
	 */
	@XStreamAlias("trade_state_desc")
	private String tradeStateDesc;
	
	/**
	 * 代金券列表
	 */
	@XStreamOmitField
	private List<TradeSubCouponDTO> couponList;

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	public String getSubOpenId() {
		return subOpenId;
	}

	public void setSubOpenId(String subOpenId) {
		this.subOpenId = subOpenId;
	}

	public String getSubIsSubscribe() {
		return subIsSubscribe;
	}

	public void setSubIsSubscribe(String subIsSubscribe) {
		this.subIsSubscribe = subIsSubscribe;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getTradeState() {
		return tradeState;
	}

	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public Integer getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public Integer getCashFee() {
		return cashFee;
	}

	public void setCashFee(Integer cashFee) {
		this.cashFee = cashFee;
	}

	public String getCashFeeType() {
		return cashFeeType;
	}

	public void setCashFeeType(String cashFeeType) {
		this.cashFeeType = cashFeeType;
	}

	public Integer getSettlementTotalFee() {
		return settlementTotalFee;
	}

	public void setSettlementTotalFee(Integer settlementTotalFee) {
		this.settlementTotalFee = settlementTotalFee;
	}

	public Integer getCouponFee() {
		return couponFee;
	}

	public void setCouponFee(Integer couponFee) {
		this.couponFee = couponFee;
	}

	public Integer getCouponCount() {
		return couponCount;
	}

	public void setCouponCount(Integer couponCount) {
		this.couponCount = couponCount;
	}

	public Integer getCouponType() {
		return couponType;
	}

	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getTradeStateDesc() {
		return tradeStateDesc;
	}

	public void setTradeStateDesc(String tradeStateDesc) {
		this.tradeStateDesc = tradeStateDesc;
	}

	public List<TradeSubCouponDTO> getCouponList() {
		return couponList;
	}

	public void setCouponList(List<TradeSubCouponDTO> couponList) {
		this.couponList = couponList;
	}

	@Override
	public String toString() {
		return "PayNotifyRequest [signType=" + signType + ", deviceInfo="
				+ deviceInfo + ", openId=" + openId + ", isSubscribe="
				+ isSubscribe + ", subOpenId=" + subOpenId
				+ ", subIsSubscribe=" + subIsSubscribe + ", tradeType="
				+ tradeType + ", tradeState=" + tradeState + ", bankType="
				+ bankType + ", totalFee=" + totalFee + ", feeType=" + feeType
				+ ", cashFee=" + cashFee + ", cashFeeType=" + cashFeeType
				+ ", settlementTotalFee=" + settlementTotalFee + ", couponFee="
				+ couponFee + ", couponCount=" + couponCount + ", couponType="
				+ couponType + ", couponId=" + couponId + ", transactionId="
				+ transactionId + ", outTradeNo=" + outTradeNo + ", attach="
				+ attach + ", timeEnd=" + timeEnd + ", tradeStateDesc="
				+ tradeStateDesc + ", couponList=" + couponList
				+ ", toString()=" + super.toString() + "]";
	}

}
