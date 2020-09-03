package zy.com.cn.sicily.web.service;

import zy.com.cn.sicily.web.model.UserAddress;

import java.util.List;

/**
 * 用户地址接口
 */
public interface UserAddressService {
    /**
     * 插入地址
     * @param record
     * @return
     */
    UserAddress insertAddress(UserAddress record);

    /**
     * 查找地址列表
     * @param record
     * @return
     */
    List<UserAddress> addressList(UserAddress record);
}
