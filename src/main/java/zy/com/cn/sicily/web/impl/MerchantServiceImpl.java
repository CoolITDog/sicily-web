package zy.com.cn.sicily.web.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zy.com.cn.sicily.web.mapper.MerchantMapper;
import zy.com.cn.sicily.web.model.Merchant;
import zy.com.cn.sicily.web.service.MerchantService;

/**
 * @title: MerchantServiceImpl
 * @description: 门店信息服务实现
 * @author: zhangyan
 * @date: 2020-03-14 13:32
 * @version: 1.0
 **/
@Service
public class MerchantServiceImpl implements MerchantService {

    private Logger logger = LoggerFactory.getLogger(MerchantServiceImpl.class);

    @Autowired
    private MerchantMapper merchantMapper;
    @Override
    public Merchant getMerchant() {
        try{
            return  merchantMapper.getRecord();
        }catch (Exception e){
            logger.error("getMerchant error:{}", e.getMessage(), e);
            return null;
        }
    }

    /**
     * 根据账户名获取商户信息
     *
     * @param accountName
     * @return
     */
    @Override
    public Merchant getByAccountName(String accountName) {
        try{
            return merchantMapper.getByAccountName(accountName);
        }catch(Exception e){
            logger.error("getByUserName error:{}", e.getMessage(), e);
            return null;
        }
    }
}
