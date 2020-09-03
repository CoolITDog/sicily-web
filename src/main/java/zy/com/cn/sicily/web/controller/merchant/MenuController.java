package zy.com.cn.sicily.web.controller.merchant;

import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import zy.com.cn.sicily.web.beans.ResultEntity;
import zy.com.cn.sicily.web.model.FoodCategory;
import zy.com.cn.sicily.web.service.FoodCategoryService;

/**
 * @title: MenuController
 * @description: 菜单管理
 * @author: zhangyan
 * @date: 2020-08-10 09:37
 * @version: 1.0
 **/
@RestController
@RequestMapping("/menu")
public class MenuController {

    private Logger logger = LoggerFactory.getLogger(MenuController.class);

    private static final Integer pageSize = 20;
    @Autowired
    private FoodCategoryService foodCategoryService;
    /**
     * 菜单页路径
     * @return
     */
    @GetMapping("/index")
    public ModelAndView menu(){
        logger.info("***menu");

        return new ModelAndView("menu");
    }

    /**
     * 分页查找菜单
     * @param pageNum
     * @param condition
     * @return
     */
    @RequestMapping("/page")
    public ResultEntity<PageInfo<FoodCategory>> pageCategory(Integer pageNum, FoodCategory condition){
        try{
            PageInfo<FoodCategory> result = foodCategoryService.pageCategory(pageNum,pageSize,condition);
            return ResultEntity.success(result);
        }catch (Exception e){
            logger.error("foodCategoryService.pageCategory error:{}", e.getMessage(), e);
            return ResultEntity.error(e.getMessage());
        }
    }

    /**
     * 编辑菜单
     * @param foodCategory
     * @return
     */
    @RequestMapping("/edit")
    public ResultEntity<FoodCategory> editCategory(FoodCategory foodCategory){
        try {
            FoodCategory result ;
            if(null == foodCategory.getId()){
                // 新增
                result = foodCategoryService.insertCategory(foodCategory);
            }else{
                // 修改
                result = foodCategoryService.updateCategory(foodCategory);
            }
            return ResultEntity.success(result);
        }catch (Exception e){
            logger.error("foodCategoryService.editCategory error:{}", e.getMessage(), e);
            return ResultEntity.error(e.getMessage());
        }
    }
}
