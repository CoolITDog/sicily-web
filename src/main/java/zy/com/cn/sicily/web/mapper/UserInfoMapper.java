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
    int insertRecord(UserInfo record);

    /**
     * 查询用户列表
     * @param record
     * @return
     */
    List<UserInfo> listRecord(UserInfo record);

    /**
     * 根据openId获取用户信息
     * @param openId
     * @return
     */
    UserInfo getRecordByOpenId(String openId);
}
