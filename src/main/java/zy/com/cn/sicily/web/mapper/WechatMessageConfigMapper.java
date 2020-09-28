package zy.com.cn.sicily.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import zy.com.cn.sicily.web.model.WechatMessageConfig;

/**
 * @title: WechatMessageConfigMapper
 * @description: 微信模板消息
 * @author: zhangyan
 * @date: 2020-09-04 10:45
 * @version: 1.0
 **/
@Mapper
public interface WechatMessageConfigMapper {

    /**
     * 根据消息类型查找模板消息
     * @param msgType
     * @return
     */
    WechatMessageConfig getByMsgType(Integer msgType);
}
