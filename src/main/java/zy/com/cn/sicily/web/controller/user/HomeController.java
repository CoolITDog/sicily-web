package zy.com.cn.sicily.web.controller.user;

import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zy.com.cn.sicily.web.beans.ResultEntity;
import zy.com.cn.sicily.web.model.FoodCategory;
import zy.com.cn.sicily.web.model.FoodInfo;
import zy.com.cn.sicily.web.model.ShopCar;
import zy.com.cn.sicily.web.service.FoodCategoryService;
import zy.com.cn.sicily.web.service.FoodInfoService;
import zy.com.cn.sicily.web.service.ShopCarService;
import java.util.List;

/**
 * @title: HomeController
 * @description: 首页
 * @author: zhangyan
 * @date: 2020-03-12 15:09
 * @version: 1.0
 **/
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private FoodCategoryService foodCategoryService;

    @Autowired
    private FoodInfoService foodInfoService;
    @Autowired
    private ShopCarService shopCarService;

    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 首页获取菜单列表
     * @return
     */
    @GetMapping("listCategory")
    @ResponseBody
    public ResultEntity<List<FoodCategory>> listFoodCategory(){
        try{
            List<FoodCategory> list = foodCategoryService.listCategory(new FoodCategory());
            logger.info("foodCategoryService.listCategory:{}", list);
            return ResultEntity.success(list);
        }catch (Exception e){
            logger.error("foodCategoryService.listCategory error:{}", e.getMessage(), e);
            return ResultEntity.error(e.getMessage());
        }
    }

    /**
     * 首页根据菜单id分页获取
     * @param categoryId
     * @return
     */
    @GetMapping("pageFood")
    @ResponseBody
    public ResultEntity<PageInfo<FoodInfo>> pageFoodsByCategoryId(Integer pageNum, Integer pageSize, Integer categoryId){
        logger.info("listFoodsByCategoryId param:{}", categoryId);
        FoodInfo condition = new FoodInfo();
        if(null != categoryId){
            condition.setCategoryId(categoryId);
        }
        try{
            PageInfo<FoodInfo> list = foodInfoService.pageFoods(pageNum, pageSize, condition);
            logger.info("foodInfoService.listFoods:{}", list);
            return ResultEntity.success(list);
        }catch (Exception e){
            logger.error("foodInfoService.listFoods: error:{}", e.getMessage(), e);
            return ResultEntity.error(e.getMessage());
        }
    }

    /**
     * 获取食物详情
     * @param id
     * @return
     */
    @GetMapping("getFoodInfo")
    @ResponseBody
    public ResultEntity<FoodInfo> getFoodInfo(Integer id){
        logger.info("getFoodInfo param:{}", id);
        try{
            FoodInfo info = foodInfoService.getFoodInfo(id);
            logger.info("foodInfoService.getFoodInfo：{}", info);
            return ResultEntity.success(info);
        }catch (Exception e){
            logger.error("foodInfoService.getFoodInfo: error:{}", e.getMessage(), e);
            return ResultEntity.error(e.getMessage());
        }
    }

    /**
     * (废弃：加入购物车操作可存储在页面上)
     * 加入购物车
     * @param shopCar
     * @return
     */
    @PostMapping("addShopCar")
    @ResponseBody
    @Deprecated
    public ResultEntity<ShopCar> addShopCar(@RequestBody ShopCar shopCar){
        logger.info("addShopCar params:{}", shopCar);
        try {
            ShopCar res = new ShopCar();
            // 搜索是否已经添加改食物
            ShopCar condition = new ShopCar();
            condition.setUserId(shopCar.getUserId());
            condition.setFoodId(shopCar.getFoodId());
            condition.setDeletedFlag("N");
            List<ShopCar> init = shopCarService.listShopCar(condition);
            logger.info("shopCarService.listShopCar :{}", init);
            if(null == init || init.size() <= 0){
                // 没有添加过该食物
                res = shopCarService.insertShopCar(condition);
                logger.info("shopCarService.insertShopCar :{}", res);
            }else{
                // 添加过改食物
                res = shopCarService.updateShopCar(shopCar);
                logger.info("shopCarService.updateShopCar :{}", res);
            }
            return ResultEntity.success(res);
        }catch (Exception e){
            logger.error("shopCarService.insertShopCar: error:{}", e.getMessage(), e);
            return ResultEntity.error(e.getMessage());
        }
    }
}
