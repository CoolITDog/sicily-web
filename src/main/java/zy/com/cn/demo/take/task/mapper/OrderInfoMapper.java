package zy.com.cn.demo.take.task.mapper;

import org.apache.ibatis.annotations.Mapper;
import zy.com.cn.demo.take.task.model.OrderInfo;

import java.util.List;

/**
 * 订单信息DAO层
 */
@Mapper
public interface OrderInfoMapper {

    /**
     * 新增订单
     * @param info
     * @return
     */
    int insertRecord(OrderInfo info);

    /**
     * 修改订单
     * @param info
     * @return
     */
    int updateRecord(OrderInfo info);

    /**
     * 根据主键查询订单详情
     * @param id
     * @return
     */
    OrderInfo getRecord(Integer id);

    /**
     * 查找订单列表
     * @param info
     * @return
     */
    List<OrderInfo> listRecord(OrderInfo info);
}
