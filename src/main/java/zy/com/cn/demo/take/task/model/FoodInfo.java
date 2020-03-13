package zy.com.cn.demo.take.task.model;

import javax.persistence.Table;

/**
 * @title: FoodInfo
 * @description: 食品信息实体对象
 * @author: zhangyan
 * @date: 2020-03-12 10:03
 * @version: 1.0
 **/
@Table(name = "sicily_food_info")
public class FoodInfo extends BaseModel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 所属菜单id
     */
    private Integer categoryId;
    /**
     * 名称
     */
    private String name;
    /**
     * 图片
     */
    private String image;
    /**
     * 今日库存
     */
    private Integer todayRepository;
    /**
     * 限购数量；0：不限购
     */
    private Integer limit;
    /**
     * 价格
     */
    private Double price;
    /**
     * 描述
     */
    private String description;
    /**
     * 是否上架。1：上架，2：下架
     */
    private String onSale;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getTodayRepository() {
        return todayRepository;
    }

    public void setTodayRepository(Integer todayRepository) {
        this.todayRepository = todayRepository;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOnSale() {
        return onSale;
    }

    public void setOnSale(String onSale) {
        this.onSale = onSale;
    }

    @Override
    public String toString() {
        return "FoodInfo{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", todayRepository=" + todayRepository +
                ", limit=" + limit +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", onSale='" + onSale + '\'' +
                "} " + super.toString();
    }
}
