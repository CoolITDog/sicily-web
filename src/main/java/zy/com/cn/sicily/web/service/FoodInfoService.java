package zy.com.cn.sicily.web.service;

import com.github.pagehelper.PageInfo;
import zy.com.cn.sicily.web.model.FoodInfo;

import java.util.List;

/**
 * @title: FoodInfoService
 * @description: 食品信息服务层
 * @author: zhangyan
 * @date: 2020-03-12 10:55
 * @version: 1.0
 **/
public interface FoodInfoService {

    /**
     * 新增食品信息
     * @param record
     * @return
     */
    FoodInfo insertFoodInfo(FoodInfo record);

    /**
     * 修改食品信息
     * @param record
     * @return
     */
    FoodInfo updateFoodInfo(FoodInfo record);

    /**
     * 根据主键获取食品信息详情
     * @param id
     * @return
     */
    FoodInfo getFoodInfo(Integer id);
    /**
     * 获取食品列表
     * @param record
     * @return
     */
    List<FoodInfo> listFoods(FoodInfo record);

    /**
     * 根据食品id查找库存数量
     * @param id
     * @return
     */
    Integer getFoodRepository(Integer id);

    /**
     * 分页查找食品信息
     * @param pageNum
     * @param pageSize
     * @param foodInfo
     * @return
     */
    PageInfo<FoodInfo> pageFoods(Integer pageNum, Integer pageSize, FoodInfo foodInfo);
}
