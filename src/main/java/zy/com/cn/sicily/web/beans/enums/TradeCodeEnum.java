package zy.com.cn.sicily.web.beans.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @title: TradeCodeEnum
 * @description: 请求码
 * @author: zhangyan
 * @date: 2020-07-24 09:37
 * @version: 1.0
 **/
public enum TradeCodeEnum implements BaseEnum<TradeCodeEnum,String>{

    // 交易状态码:成功
    SUCCESS("SUCCESS", "成功"),
    // 交易状态码:失败
    FAIL("FAIL", "失败"),
    // 交易状态码:完成
    OK("OK", "完成");

    private String value;

    private String displayName;

    private static Map<String,TradeCodeEnum> map;

    TradeCodeEnum(String value, String dispalyName){
        this.value = value;
        this.displayName = dispalyName;
        put();
    }

    public void put(){
        if(null == map){
            map = new HashMap<String,TradeCodeEnum>();
        }
        map.put(value, this);
    }

    public TradeCodeEnum get(String value){
        return map.get(value);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }
}
