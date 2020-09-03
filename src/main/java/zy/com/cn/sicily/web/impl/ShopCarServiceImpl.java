package zy.com.cn.sicily.web.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import zy.com.cn.sicily.web.beans.dto.ShopCarDTO;
import zy.com.cn.sicily.web.mapper.ShopCarMapper;
import zy.com.cn.sicily.web.model.ShopCar;
import zy.com.cn.sicily.web.service.ShopCarService;

import java.util.List;

/**
 * @title: ShopCarServiceImpl
 * @description: 购物车服务实现层
 * @author: zhangyan
 * @date: 2020-03-13 10:50
 * @version: 1.0
 **/
@Service
public class ShopCarServiceImpl implements ShopCarService {
    @Autowired
    private ShopCarMapper shopCarMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public ShopCar insertShopCar(ShopCar record) {
        Assert.notNull(record, "record is null");
        try {
            shopCarMapper.insertRecord(record);
            return record;
        }catch (Exception e){
            logger.error("新增购物车记录失败：{}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public ShopCar updateShopCar(ShopCar record) {
        Assert.notNull(record, "record is null");
        try {
            shopCarMapper.updateRecord(record);
            return record;
        }catch (Exception e){
            logger.error("修改购物车记录失败：{}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<ShopCar> listShopCar(ShopCar record) {
        Assert.notNull(record, "record is null");
        try {
            return shopCarMapper.listRecord(record);
        }catch (Exception e){
            logger.error("查找购物车记录列表：{}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<ShopCarDTO> listDetail(ShopCar record) {
        Assert.notNull(record, "record is null");
        try {
            return shopCarMapper.listDetail(record);
        }catch (Exception e){
            logger.error("查找购物车详情列表：{}", e.getMessage(), e);
            return null;
        }
    }
}
