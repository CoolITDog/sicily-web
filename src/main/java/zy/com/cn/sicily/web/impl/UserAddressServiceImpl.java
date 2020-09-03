package zy.com.cn.sicily.web.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import zy.com.cn.sicily.web.mapper.UserAddressMapper;
import zy.com.cn.sicily.web.model.UserAddress;
import zy.com.cn.sicily.web.service.UserAddressService;

import java.util.List;

/**
 * @title: UserAddressServiceImpl
 * @description: 用户地址服务
 * @author: zhangyan
 * @date: 2020-09-03 15:56
 * @version: 1.0
 **/
@Service
public class UserAddressServiceImpl implements UserAddressService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserAddressMapper userAddressMapper;
    /**
     * 插入地址
     * @param record
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UserAddress insertAddress(UserAddress record) {
        Assert.isNull(record, "record is null");
        try{
            userAddressMapper.insertAddress(record);
            return record;
        }catch (Exception e){
            logger.error("userAddressMapper.insertAddress error:{}", e.getMessage(), e);
            return null;
        }
    }

    /**
     * 查找地址列表
     *
     * @param record
     * @return
     */
    @Override
    public List<UserAddress> addressList(UserAddress record) {
       try{
           return userAddressMapper.addressList(record);
       }catch (Exception e){
           logger.error("userAddressMapper.addressList error:{}", e.getMessage(), e);
           return null;
       }
    }
}
