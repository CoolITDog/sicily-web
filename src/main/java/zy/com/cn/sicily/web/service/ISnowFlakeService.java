package zy.com.cn.sicily.web.service;

/**
 * @title: ISnowFlakeService
 * @description: 雪花id生产器
 * @author: zhangyan
 * @date: 2020-09-10 14:55
 * @version: 1.0
 **/
public interface ISnowFlakeService {
    long getSnowFlakeID();

    long[] getSnowFlakeIDs(int size);
}
