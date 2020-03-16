package zy.com.cn.sicily.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import zy.com.cn.sicily.web.model.Merchant;

/**
 * @title: MerchantMapper
 * @description: 商户信息DAO层
 * @author: zhangyan
 * @date: 2020-03-13 16:56
 * @version: 1.0
 **/
@Mapper
public interface MerchantMapper {

    /**
     * 获取商户主体信息
     * @return
     */
    Merchant getRecord();
}
