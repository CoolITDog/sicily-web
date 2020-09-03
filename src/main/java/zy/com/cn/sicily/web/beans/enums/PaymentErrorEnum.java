package zy.com.cn.sicily.web.beans.enums;

import java.util.HashMap;
import java.util.Map;

public enum PaymentErrorEnum {
    // 服务器异常
    SERVER_ERROR("1001", "服务器异常"),

    // 业务异常
    SERVICE_ERROR("1002", "业务异常"),
    // 支付结果通知异常
    PAYMENT_NOTIFY_ERROR("100201", "支付结果通知异常"),
    // 退款结果通知解密异常
    REFUND_INFO_DECRYPT_ERROR("100202", "退款结果通知解密异常"),

    // 验签失败
    VALIDATE_FAILED("2001", "验签失败"),
    // 统一下单失败
    UNIFIED_ORDER_FAILED("200101", "统一下单失败"),
    // 查询订单失败
    QUERY_ORDER_FAILED("200102", "查询订单失败"),
    // 支付结果通知验签失败
    PAYMENT_NOTIFY_VERIFY_FAILED("200103", "支付结果通知验签失败"),
    // 关闭交易订单失败
    CLOSE_ORDER_FAILED("200104", "关闭交易订单失败"),
    // 订单退款通知失败
    ORDER_REFUND_NOTIFY_FAILED("200105", "订单退款通知失败"),
    // 订单退款失败
    ORDER_REFUND_FAILED("200106", "订单退款失败"),
    // 订单退款查询失败
    ORDER_REFUND_QUERY_FAILED("200107", "订单退款失败"),

    // 参数为空
    PARAMETER_NULL("2002", "参数为空"),
    // 交易订单号为空
    OUT_TRADE_NO_NULL("200201", "交易订单号为空"),
    // 微信应用ID为空
    APPID_NULL("200202", "微信应用ID为空"),
    // 特约商户类型为空
    SPECIAL_MERCHANT_TYPE_NULL("200203", "特约商户类型为空"),
    // 支付结果通知报文为空
    PAYMENT_NOTIFY_XML_NULL("200204", "支付结果通知报文为空"),
    // 退款订单号为空
    OUT_REFUND_NO_NULL("200205", "退款订单号为空"),

    // 数据库异常
    DATABASE_ERROR("3001", "数据库异常"),

    // 数据已存在
    DATA_EXIST("4001", "数据已存在"),
    // 特约商户关系已存在
    SPECIAL_MERCHANT_REF_EXIST("400101", "特约商户关系已存在"),

    // 数据不存在
    DATA_NOT_EXIST("4002", "数据不存在"),
    // 交易订单不存在
    TRADE_ORDER_NOT_EXIST("400201", "交易订单不存在"),
    // 退款订单不存在
    REFUND_ORDER_NOT_EXIST("400202", "退款订单不存在"),

    // 数据无效
    DATA_INVALID("4003", "数据无效");

    private String value;

    private String displayName;

    static Map<String, PaymentErrorEnum> enumMap = new HashMap<String, PaymentErrorEnum>();

    static{
        for(PaymentErrorEnum code:PaymentErrorEnum.values()){
            enumMap.put(code.getValue(), code);
        }
    }

    PaymentErrorEnum(String value, String displayName){
        this.value = value;
        this.displayName = displayName;
    }

    public String getValue() {
        return value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static PaymentErrorEnum getEnum(String value){
        return enumMap.get(value);
    }

    public String toString() {
        return this.name().concat("[value:").concat(getValue()).concat(", displayName:").concat(getDisplayName()).concat("]");
    }
}
