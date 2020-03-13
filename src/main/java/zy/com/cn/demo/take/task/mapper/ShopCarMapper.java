package zy.com.cn.demo.take.task.mapper;

import org.apache.ibatis.annotations.Mapper;
import zy.com.cn.demo.take.task.beans.dto.ShopCarDTO;
import zy.com.cn.demo.take.task.model.ShopCar;

import java.util.List;

/**
 * 购物车信息DAO层
 */
@Mapper
public interface ShopCarMapper {

    /**
     * 插入购物车记录
     * @param record
     * @return
     */
    int insertRecord(ShopCar record);

    /**
     * 更新购物车
     * @param record
     * @return
     */
    int updateRecord(ShopCar record);

    /**
     * 查询购物车列表
     * @param record
     * @return
     */
    List<ShopCar> listRecord(ShopCar record);

    /**
     * 查询购物车详情列表
     * @param record
     * @return
     */
    List<ShopCarDTO> listDetail(ShopCar record);
}
