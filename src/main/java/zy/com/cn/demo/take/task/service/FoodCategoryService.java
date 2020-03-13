package zy.com.cn.demo.take.task.service;


import zy.com.cn.demo.take.task.model.FoodCategory;

import java.util.List;

/**
 * @title: FoodCategoryService
 * @description: 食物菜单服务层
 * @author: zhangyan
 * @date: 2020-03-10 16:27
 * @version: 1.0
 **/

public interface FoodCategoryService {
    /**
     * 新增食品菜单
     * @param record
     * @return
     * @throws Exception
     */
    FoodCategory insertCategory(FoodCategory record);

    /**
     * 修改菜单
     * @param record
     * @return
     */
    FoodCategory updateCategory(FoodCategory record);

    /**
     * 查找菜单列表
     * @param record
     * @return
     */
    List<FoodCategory> listCategory(FoodCategory record);
}
