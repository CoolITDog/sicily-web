package zy.com.cn.sicily.web.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zy.com.cn.sicily.web.beans.ResultEntity;
import zy.com.cn.sicily.web.model.UserAddress;
import zy.com.cn.sicily.web.model.UserInfo;
import zy.com.cn.sicily.web.service.UserAddressService;
import zy.com.cn.sicily.web.utils.Constants;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @title: AddressController
 * @description: 地址控制器
 * @author: zhangyan
 * @date: 2020-09-03 16:01
 * @version: 1.0
 **/
@Controller
@RequestMapping("/address")
public class AddressController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private HttpSession session;

    /**
     * 获取地址列表
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    @ResponseBody
    public ResultEntity<List<UserAddress>> addressList(){
        if(null == session.getAttribute(Constants.SESSION_USERINFO_KEY)){
            return ResultEntity.error("获取用户数据失败");
        }
        try {
            UserInfo userInfo = (UserInfo) session.getAttribute(Constants.SESSION_USERINFO_KEY);
            UserAddress record = new UserAddress();
            record.setMobile(userInfo.getMobile());
            return ResultEntity.success(userAddressService.addressList(record));
        }catch (Exception e){
            logger.error("userAddressService.insertAddress error:{}", e.getMessage(), e);
            return ResultEntity.error(e.getMessage());
        }
    }

    /**
     * 新增地址
     * @param record
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ResponseBody
    public ResultEntity<UserAddress> insertAddress(@RequestBody UserAddress record){
        logger.info("insertAddress record:{}", record);
        try {
            return ResultEntity.success(userAddressService.insertAddress(record));
        }catch (Exception e){
            logger.error("userAddressService.insertAddress error:{}", e.getMessage(), e);
            return ResultEntity.error(e.getMessage());
        }
    }
}
