package zy.com.cn.demo.take.task.mapper;

import org.apache.ibatis.annotations.Mapper;
import zy.com.cn.demo.take.task.model.UserInfo;

import java.util.List;

/**
 * 用户信息DAO层
 */
@Mapper
public interface UserInfoMapper {

    /**
     * 新增用户
     * @param record
     * @return
     */
    int insertRecord(UserInfo record);

    /**
     * 查询用户列表
     * @param record
     * @return
     */
    List<UserInfo> listRecord(UserInfo record);
}
