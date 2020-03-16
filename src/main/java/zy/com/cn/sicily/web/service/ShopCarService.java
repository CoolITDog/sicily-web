package zy.com.cn.sicily.web.service;

import zy.com.cn.sicily.web.beans.dto.ShopCarDTO;
import zy.com.cn.sicily.web.model.ShopCar;

import java.util.List;

/**
 * @title: ShopCarService
 * @description: 购物车服务层
 * @author: zhangyan
 * @date: 2020-03-13 10:48
 * @version: 1.0
 **/
public interface ShopCarService {
    /**
     * 插入购物车记录
     * @param record
     * @return
     */
    ShopCar insertShopCar(ShopCar record);

    /**
     * 更新购物车
     * @param record
     * @return
     */
    ShopCar updateShopCar(ShopCar record);

    /**
     * 查询购物车列表
     * @param record
     * @return
     */
    List<ShopCar> listShopCar(ShopCar record);

    /**
     * 查询购物车详情列表
     * @param record
     * @return
     */
    List<ShopCarDTO> listDetail(ShopCar record);
}
