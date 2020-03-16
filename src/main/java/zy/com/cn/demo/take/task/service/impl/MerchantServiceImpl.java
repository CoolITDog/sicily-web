package zy.com.cn.demo.take.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zy.com.cn.demo.take.task.mapper.MerchantMapper;
import zy.com.cn.demo.take.task.model.Merchant;
import zy.com.cn.demo.take.task.service.MerchantService;

/**
 * @title: MerchantServiceImpl
 * @description: 门店信息服务实现
 * @author: zhangyan
 * @date: 2020-03-14 13:32
 * @version: 1.0
 **/
@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantMapper merchantMapper;
    @Override
    public Merchant getMerchanr() {
        try{
            return  merchantMapper.getRecord();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}