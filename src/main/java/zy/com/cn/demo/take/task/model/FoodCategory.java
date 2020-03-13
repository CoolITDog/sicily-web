package zy.com.cn.demo.take.task.model;

import javax.persistence.Table;

/**
 * @title: FoodCategory
 * @description: 食品菜单实体
 * @author: zhangyan
 * @date: 2020-03-10 16:01
 * @version: 1.0
 **/
@Table(name = "sicily_food_category")
public class FoodCategory extends BaseModel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 名称
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FoodCategory{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
