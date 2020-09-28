package zy.com.cn.sicily.web.service;

import zy.com.cn.sicily.web.model.WechatTradeOrder;

/**
 * 微信交易订单服务
 */
public interface WechatTradeOrderService {
    /**
     * 新增
     * @param order
     * @return
     */
    WechatTradeOrder insertRecord(WechatTradeOrder order);

    /**
     * 根据交易号更新
     * @param order
     * @return
     */
    WechatTradeOrder updateRecordByOutTradeNo(WechatTradeOrder order);
}
