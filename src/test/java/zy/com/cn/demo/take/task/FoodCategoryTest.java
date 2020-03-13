package zy.com.cn.demo.take.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import zy.com.cn.demo.take.task.mapper.FoodCategoryMapper;
import zy.com.cn.demo.take.task.model.FoodCategory;
import zy.com.cn.demo.take.task.service.FoodCategoryService;

import java.util.List;

/**
 * @title: FoodCategoryTest
 * @description: 食物菜单接口测试用例
 * @author: zhangyan
 * @date: 2020-03-10 16:43
 * @version: 1.0
 **/
public class FoodCategoryTest extends DemoTakeTaskApplicationTests {

    @Autowired
    private FoodCategoryService foodCategoryService;

    @Test
    public void insertRecordTest(){
        FoodCategory record = new FoodCategory();
        record.setName("软欧");
        record.setCreatedBy("sicily");
        FoodCategory result = foodCategoryService.insertCategory(record);
        System.out.println(result);
    }

    @Test
    public void updateCategoryTest(){
        FoodCategory record = new FoodCategory();
        record.setId(1);
        record.setName("吐司");
        FoodCategory result = foodCategoryService.updateCategory(record);
        System.out.println(result);
    }

    @Test
    public void listCategoryTest(){
        FoodCategory record = new FoodCategory();
        List<FoodCategory> result = foodCategoryService.listCategory(record);
        System.out.println(result);
    }
}
