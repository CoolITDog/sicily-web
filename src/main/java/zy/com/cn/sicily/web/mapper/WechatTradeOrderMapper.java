package zy.com.cn.sicily.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import zy.com.cn.sicily.web.model.WechatTradeOrder;

/**
 * @title: WechatTradeOrder
 * @description: 微信支付交易记录
 * @author: zhangyan
 * @date: 2020-08-12 16:49
 * @version: 1.0
 **/
@Mapper
public interface WechatTradeOrderMapper {

    /**
     * 新增
     * @param order
     * @return
     */
    int insertRecord(WechatTradeOrder order);

    /**
     * 根据交易号修改
     * @param order
     * @return
     */
    int updateRecordByOutTradeNo(WechatTradeOrder order);
}
