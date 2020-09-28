package zy.com.cn.sicily.web.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import zy.com.cn.sicily.web.exception.SnowFlakeCustomException;
import zy.com.cn.sicily.web.service.ISnowFlakeService;
import zy.com.cn.sicily.web.utils.SnowflakeIdWorker;

/**
 * @title: ISnowFlakeServiceImpl
 * @description: 雪花id生产器
 * @author: zhangyan
 * @date: 2020-09-10 14:55
 * @version: 1.0
 **/
@Service
public class ISnowFlakeServiceImpl implements ISnowFlakeService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public long getSnowFlakeID() {
        long id = SnowflakeIdWorker.getId();
        logger.info("id: {}", id);
        return id;
    }

    @Override
    public long[] getSnowFlakeIDs(int size) {
        if (size < 1) {
            throw new SnowFlakeCustomException(" size is illegal");
        }
        long[] ids = new long[size];
        for (int i = 0; i < size; i++) {
            long id = SnowflakeIdWorker.getId();
            ids[i] = id;
            logger.info("id: {}", id);
        }
        return ids;
    }
}
