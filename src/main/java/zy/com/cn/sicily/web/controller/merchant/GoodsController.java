package zy.com.cn.sicily.web.controller.merchant;

import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import zy.com.cn.sicily.web.beans.ResultEntity;
import zy.com.cn.sicily.web.model.FoodInfo;
import zy.com.cn.sicily.web.service.CommonService;
import zy.com.cn.sicily.web.service.FoodInfoService;


/**
 * @title: GoodsController
 * @description: 商品管理
 * @author: zhangyan
 * @date: 2020-08-10 16:59
 * @version: 1.0
 **/
@RestController
@RequestMapping("/goods")
public class GoodsController {

    private Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private FoodInfoService foodInfoService;
    @Autowired
    private CommonService commonService;

    /**
     * 商品管理页
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView goods(){
        return new ModelAndView("goods");
    }

    /**
     * 分页查询商品
     * @param pageNum
     * @param pageSize
     * @param foodInfo
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public ResultEntity<PageInfo<FoodInfo>> pageFood(Integer pageNum, Integer pageSize, FoodInfo foodInfo){
        try{
            PageInfo<FoodInfo> res = foodInfoService.pageFoods(pageNum, pageSize, foodInfo);
            return ResultEntity.success(res);
        }catch (Exception e){
            logger.error("pageFood error:{}", e.getMessage(), e);
            return ResultEntity.error(e.getMessage());
        }
    }

    /**
     * 编辑商品
     * @param foodInfo
     * @return
     */
    @RequestMapping("/edit")
    public ResultEntity<FoodInfo> editFood(@RequestBody  FoodInfo foodInfo){
        logger.info("editFood:{}", foodInfo);
        try {
            FoodInfo result;
            if(null == foodInfo.getId()){
                result = foodInfoService.insertFoodInfo(foodInfo);
            }else {
                result = foodInfoService.updateFoodInfo(foodInfo);
            }
            return ResultEntity.success(result);
        }catch (Exception e){
            logger.error("editFood error:{}", e.getMessage(), e);
            return ResultEntity.error(e.getMessage());
        }
    }

    /**
     * 上传图片
     * @param file
     * @return
     */
    @RequestMapping(value = "uploadImage")
    public ResultEntity<String> uploadImage(@RequestParam("file")MultipartFile file){
        return upload(file, "image");
    }

    /**
     * 文件上传
     * @param file
     * @param fileType 文件类型（file:所有文件类型；video:视频；image:图片）
     * @return
     * @author Alvin
     * @date 2019年5月1日 下午7:46:21
     */
    @RequestMapping("upload")
    public ResultEntity<String> upload(@RequestParam(value = "file")MultipartFile file, String fileType){
        return ResultEntity.success(commonService.upload(file,fileType));
    }
}
