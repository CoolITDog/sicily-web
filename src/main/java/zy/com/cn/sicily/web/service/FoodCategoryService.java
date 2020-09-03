package zy.com.cn.sicily.web.service;


import com.github.pagehelper.PageInfo;
import zy.com.cn.sicily.web.beans.pages.Pager;
import zy.com.cn.sicily.web.model.FoodCategory;

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

    /**
     * 分页查找菜单
     * @param pageNum
     * @param pageSize
     * @param condition
     * @return
     */
    PageInfo<FoodCategory> pageCategory(int pageNum, int pageSize, FoodCategory condition);
}
