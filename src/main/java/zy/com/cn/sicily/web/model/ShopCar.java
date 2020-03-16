package zy.com.cn.sicily.web.model;

import javax.persistence.Table;

/**
 * @title: ShopCar
 * @description: 购物车实体对象
 * @author: zhangyan
 * @date: 2020-03-13 09:41
 * @version: 1.0
 **/
@Table(name = "sicily_shop_car")
public class ShopCar extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 食物id
     */
    private Integer foodId;

    /**
     * 选购数量
     */
    private Integer amount;

    /**
     * 购买状态：1：可购买，2：已售罄，3：已下架
     */
    private Integer status;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ShopCar{" +
                "userId=" + userId +
                ", foodId=" + foodId +
                ", amount=" + amount +
                ", status=" + status +
                "} " + super.toString();
    }
}
