package zy.com.cn.sicily.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import zy.com.cn.sicily.web.model.UserAddress;

import java.util.List;

/**
 * @title: UserAddressMapper
 * @description: 用户地址维护
 * @author: zhangyan
 * @date: 2020-09-03 15:48
 * @version: 1.0
 **/
@Mapper
public interface UserAddressMapper {

    /**
     * 插入地址
     * @param record
     * @return
     */
    int insertAddress(UserAddress record);

    /**
     * 查找地址列表
     * @param record
     * @return
     */
    List<UserAddress> addressList(UserAddress record);
}
