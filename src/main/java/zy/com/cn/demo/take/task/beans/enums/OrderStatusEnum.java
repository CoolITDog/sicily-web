package zy.com.cn.demo.take.task.beans.enums;

/**
 * 订单状态枚举
 */
public enum OrderStatusEnum implements BaseEnum<OrderStatusEnum, Integer>{
    /**
     * 1：已下单
     */
    ORDERD(1, "已下单"),
    /**
     * 2：已接单
     */
    CHECKED(2, "已接单"),
    /**
     * 3：已取消
     */
    CANCEL(3, "已取消"),
    /**
     * 4：完成
     */
    SUCCESSED(4, "已完成");

    private Integer value;

    private String displayName;

    OrderStatusEnum(Integer value, String displayName){
        this.value = value;
        this.displayName = displayName;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }
}
