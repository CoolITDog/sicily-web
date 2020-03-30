package zy.com.cn.sicily.web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;
import zy.com.cn.sicily.web.mapper.FoodCategoryMapper;
import zy.com.cn.sicily.web.model.FoodCategory;
import zy.com.cn.sicily.web.service.FoodCategoryService;

import java.util.List;

/**
 * @title: FoodCategoryServiceImpl
 * @description: 食物菜单接口暴露
 * @author: zhangyan
 * @date: 2020-03-10 16:38
 * @version: 1.0
 **/
@Service
public class FoodCategoryServiceImpl implements FoodCategoryService {

    @Autowired
    private FoodCategoryMapper foodCategoryMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 新增食品菜单(声明式事务管理@Transcational)
     * @param record 食品菜单
     * @return foodCategory
     * @throws Exception 异常信息
     */
    @Override
    @Transactional(propagation =  Propagation.REQUIRED, rollbackFor = {Exception.class})
    public FoodCategory insertCategory(FoodCategory record) {
        Assert.notNull(record, "foodCategory record is null");
        try{
            foodCategoryMapper.insertRecord(record);
            return record;
        }catch (Exception e){
            logger.error("新增菜单失败:{}", e.getMessage(), e);
            return null;
        }
    }
    /**
     * 修改菜单（编程式事务管理 使用TransactionTemplate）
     * 编程式事务管理 还可以使用PlatformTransactionManager
     * @param record
     * @return
     */
    @Override
    public FoodCategory updateCategory(FoodCategory record) {
        Assert.notNull(record, "foodCategory record is null");
        TransactionTemplate tt = new TransactionTemplate();
        Object result = tt.execute(
                new TransactionCallback() {
                    @Override
                    public Object doInTransaction(TransactionStatus status){
                        foodCategoryMapper.updateRecord(record);
                        return record;
                    }
                }
        );
        return (FoodCategory) result;
    }
    /**
     * 查找菜单列表
     * @param record
     * @return
     */
    @Override
    public List<FoodCategory> listCategory(FoodCategory record) {
        Assert.notNull(record, "foodCategory record is null");
        try{
            return foodCategoryMapper.listRecord(record);
        }catch(Exception e){
            logger.error("查找菜单列表：{}", e.getMessage(), e);
            return null;
        }
    }
}
