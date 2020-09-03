package zy.com.cn.sicily.web.service;

import zy.com.cn.sicily.web.model.Merchant;

/**
 * @title: MerchantService
 * @description: 门店信息服务
 * @author: zhangyan
 * @date: 2020-03-14 13:31
 * @version: 1.0
 **/
public interface MerchantService {

    /**
     * 获取门店信息
     * @return
     */
    Merchant getMerchant();

    /**
     * 根据账户名获取商户信息
     * @param userName
     * @return
     */
    Merchant getByAccountName(String accountName);
}
