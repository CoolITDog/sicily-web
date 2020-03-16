package zy.com.cn.sicily.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import zy.com.cn.sicily.web.model.FoodInfo;
import zy.com.cn.sicily.web.service.FoodInfoService;

import java.util.List;

/**
 * @title: FoodInfoTest
 * @description: 食品信息接口测试
 * @author: zhangyan
 * @date: 2020-03-12 11:10
 * @version: 1.0
 **/
public class FoodInfoTest extends DemoTakeTaskApplicationTests {
    @Autowired
    private FoodInfoService foodInfoService;

    @Test
    public void insertFoodInfoTest(){
        FoodInfo info = new FoodInfo();
        info.setCategoryId(1);
        info.setName("白切片");
        info.setDescription("无糖");
        info.setImage("123");
        info.setLimit(1);
        info.setOnSale("1");
        info.setPrice(18.00);
        info.setTodayRepository(10);
        info.setCreatedBy("sicily");
        FoodInfo res = foodInfoService.insertFoodInfo(info);
        System.out.println(res);
    }
    @Test
    public void updateFoodInfoTest(){
        FoodInfo info = new FoodInfo();
        info.setPrice(18.50);
        info.setId(2);
        FoodInfo res = foodInfoService.updateFoodInfo(info);
        System.out.println(res);
    }
    @Test
    public void getFoodInfoTest(){
        FoodInfo res = foodInfoService.getFoodInfo(2);
        System.out.println(res);
    }
    @Test
    public void listFoodsTest(){
        FoodInfo info = new FoodInfo();
        info.setCategoryId(1);
        List<FoodInfo> list = foodInfoService.listFoods(info);
        System.out.println(list);
    }
}
