package zy.com.cn.sicily.web.beans.dto;

import zy.com.cn.sicily.web.model.ShopCar;

/**
 * @title: ShopCarDTO
 * @description: 购物车详情传输对象
 * @author: zhangyan
 * @date: 2020-03-13 10:04
 * @version: 1.0
 **/
public class ShopCarDTO extends ShopCar {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;
    /**
     * 图片
     */
    private String image;
    /**
     * 价格
     */
    private Double price;
    /**
     * 描述
     */
    private String description;

    /**
     * 限购数量；0：不限购
     */
    private Integer limit;

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

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "ShopCarDTO{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", limit=" + limit +
                "} " + super.toString();
    }
}
