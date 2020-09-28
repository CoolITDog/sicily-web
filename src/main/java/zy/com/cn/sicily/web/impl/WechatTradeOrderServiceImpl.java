package zy.com.cn.sicily.web.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zy.com.cn.sicily.web.mapper.WechatTradeOrderMapper;
import zy.com.cn.sicily.web.model.WechatTradeOrder;
import zy.com.cn.sicily.web.service.WechatTradeOrderService;

import java.util.Date;

/**
 * @title: WechatTradeOrderServiceImpl
 * @description: 微信交易订单服务
 * @author: zhangyan
 * @date: 2020-09-04 16:49
 * @version: 1.0
 **/
@Service
public class WechatTradeOrderServiceImpl implements WechatTradeOrderService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private WechatTradeOrderMapper wechatTradeOrderMapper;
    /**
     * 新增
     *
     * @param order
     * @return
     */
    @Override
    public WechatTradeOrder insertRecord(WechatTradeOrder order) {
        try{
            order.setCreatedDate(new Date());
            order.setDeletedFlag("N");
            wechatTradeOrderMapper.insertRecord(order);
            return order;
        }catch (Exception e){
            logger.error("wechatTradeOrderMapper.insertRecord error:{}", e.getMessage(), e);
            return null;
        }
    }

    /**
     * 根据交易号更新
     *
     * @param order
     * @return
     */
    @Override
    public WechatTradeOrder updateRecordByOutTradeNo(WechatTradeOrder order) {
        if(null == order || null == order.getOutTradeNo()){
            return null;
        }
        try {
            wechatTradeOrderMapper.updateRecordByOutTradeNo(order);
            return order;
        }catch (Exception e){
            logger.error("wechatTradeOrderMapper.updateRecordByOutTradeNo error:{}", e.getMessage(), e);
            return null;
        }
    }
}
