package zy.com.cn.sicily.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import zy.com.cn.sicily.web.model.WeChatConfig;

@Mapper
public interface WeChatConfigMapper {

    /**
     * 根据配置名称获取配置内容
     * @param configName
     * @return
     */
    WeChatConfig getByName(String configName);
}
