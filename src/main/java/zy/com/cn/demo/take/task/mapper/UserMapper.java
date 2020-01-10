package zy.com.cn.demo.take.task.mapper;

import org.apache.ibatis.annotations.Mapper;
import zy.com.cn.demo.take.task.model.User;

/**
 * @title: UserMapper
 * @description: 用户服务dao层
 * @author: zhangyan
 * @date: 2020-01-07 15:18
 * @version: 1.0
 **/
public interface UserMapper {
    int insert(User user);
}
