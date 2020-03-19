package zy.com.cn.sicily.web.beans.enums;

/**
 * @title: DefaultErrorEnum
 * @description: 错误枚举
 * @author: zhangyan
 * @date: 2020-03-18 15:09
 * @version: 1.0
 **/
public enum  DefaultErrorEnum implements BaseEnum<DefaultErrorEnum, String>{
    DATA_NULL("1001", "数据为空"),
    SERVICE_ERROR("1002", "服务出错");

    private String value;

    private String displayName;

    DefaultErrorEnum(String value, String displayName){
        this.value = value;
        this.displayName = displayName;
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
