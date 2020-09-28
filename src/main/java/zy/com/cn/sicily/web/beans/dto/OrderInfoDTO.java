package zy.com.cn.sicily.web.beans.dto;

import zy.com.cn.sicily.web.model.OrderFood;
import zy.com.cn.sicily.web.model.OrderInfo;

import java.util.List;

/**
 * @title:
 * @description: 订单详情传输对象
 * @author: zhangyan
 * @date: 2020-03-12 17:05
 * @version: 1.0
 **/
public class OrderInfoDTO extends OrderInfo {

    private static final long serialVersionUID = 1L;

    /**
     * 订单的食物列表
     */
    private List<OrderFood> foods;

    /**
     * 下单失败列表
     */
    private List<OrderFood> failList;

    /**
     * 没有全部下单成功提示信息
     */
    private String errMsg;

    public List<OrderFood> getFoods() {
        return foods;
    }

    public void setFoods(List<OrderFood> foods) {
        this.foods = foods;
    }

    public List<OrderFood> getFailList() {
        return failList;
    }

    public void setFailList(List<OrderFood> failList) {
        this.failList = failList;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "OrderInfoDTO{" +
                "foods=" + foods +
                ", failList=" + failList +
                ", errMsg='" + errMsg + '\'' +
                "} " + super.toString();
    }
}
