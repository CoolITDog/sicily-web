package zy.com.cn.sicily.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import zy.com.cn.sicily.web.model.OrderFood;

import java.util.List;

/**
 * 订单食品映射DAO层
 */
@Mapper
public interface OrderFoodMapper {

    /**
     * 新增订单食品记录
     * @param record
     * @return
     */
    int insertRecord(OrderFood record);

    /**
     * 查找食品列表
     * @param record
     * @return
     */
    List<OrderFood> listRecords(OrderFood record);
}
