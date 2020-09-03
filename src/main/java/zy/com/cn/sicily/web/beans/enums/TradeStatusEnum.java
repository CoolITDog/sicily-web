package zy.com.cn.sicily.web.beans.enums;

import java.util.HashMap;
import java.util.Map;

public enum TradeStatusEnum implements BaseEnum<TradeStatusEnum, String>{

    // 交易状态:支付成功
    SUCCESS("SUCCESS", "支付成功"),
    // 交易状态:转入退款
    REFUND("REFUND", "转入退款"),
    // 交易状态:未支付
    NOTPAY("NOTPAY", "未支付"),
    // 交易状态:已关闭
    CLOSED("CLOSED", "已关闭"),
    // 交易状态:已撤销
    REVOKED("REVOKED", "已撤销"),
    // 交易状态:用户支付中
    USERPAYING("USERPAYING", "用户支付中"),
    // 交易状态:支付失败
    PAYERROR("PAYERROR", "支付失败");

    private String value;

    private String displayName;

    private static Map<String, TradeStatusEnum> map;

    private TradeStatusEnum(String value, String displayName){
        this.value = value;
        this.displayName = displayName;
        put();
    }
    private void put(){
        if(map == null){
            map = new HashMap<String, TradeStatusEnum>();
        }
        map.put(value, this);
    }

    public static TradeStatusEnum get(String value){
        return map.get(value);
    }

    @Override
    public String toString() {
        return this.name().concat("[value:").concat(getValue().toString()).concat(", displayName:").concat(getDisplayName()).concat("]");
    }
    @Override
    public String getValue() {
        return null;
    }

    @Override
    public String getDisplayName() {
        return null;
    }
}
