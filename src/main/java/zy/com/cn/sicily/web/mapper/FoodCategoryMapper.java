package zy.com.cn.sicily.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import zy.com.cn.sicily.web.model.FoodCategory;

import java.util.List;

/**
 * @title: FoodCategoryMapper
 * @description: 食品菜单Dao层
 * @author: zhangyan
 * @date: 2020-03-10 16:01
 * @version: 1.0
 */
@Mapper
public interface FoodCategoryMapper {
    /**
     * 新增菜单项
     * @param record
     * @return
     */
    int insertRecord(FoodCategory record);

    /**
     * 修改菜单项
     * @param record
     * @return
     */
    int updateRecord(FoodCategory record);

    /**
     * 获取菜单列表
     * @param record
     * @return
     */
    List<FoodCategory> listRecord(FoodCategory record);

}
