package zy.com.cn.sicily.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import zy.com.cn.sicily.web.model.FoodInfo;

import java.util.List;

/**
 * @Title FoodInfoMapper
 * @description: 食品信息Dao层
 * @author: zhangyan
 * @date: 2020-03-10 16:01
 * @version: 1.0
 */
@Mapper
public interface FoodInfoMapper {

    /**
     * 新增食品
     * @param record
     * @return
     */
    int insertRecord(FoodInfo record);

    /**
     * 修改食品
     * @param record
     * @return
     */
    int updateRecord(FoodInfo record);
    /**
     * 根据主键查询食品详情
     * @param id
     * @return
     */
    FoodInfo getRecord(Integer id);

    /**
     * 根据主键id查找食品库存
     * @param id
     * @return
     */
    int getFoodRepository(Integer id);

    /**
     * 查询食品信息
     * @param record
     * @return
     */
    List<FoodInfo> listRecord(FoodInfo record);
}
