package zy.com.cn.sicily.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import zy.com.cn.sicily.web.model.UserInfo;

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
    int insertUser(UserInfo record);

    /**
     * 更新用户
     * @param record
     * @return
     */
    int updateUser(UserInfo record);

    /**
     * 查询用户列表
     * @param record
     * @return
     */
    List<UserInfo> userList(UserInfo record);

    /**
     * 根据openId获取用户信息
     * @param openId
     * @return
     */
    UserInfo getRecordByOpenId(String openId);

    /**
     * 条件查找
     * @param userInfo
     * @return
     */
    UserInfo getOne(UserInfo userInfo);
}
