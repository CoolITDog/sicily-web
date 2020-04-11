package zy.com.cn.sicily.web.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import zy.com.cn.sicily.web.service.FoodInfoService;
import zy.com.cn.sicily.web.utils.Constants;
import zy.com.cn.sicily.web.utils.RedisUtil;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.UUID;

/**
 * @title: RedisCache
 * @description: 库存缓存redis
 * @author: zhangyan
 * @date: 2020-04-11 16:25
 * @version: 1.0
 **/
@Component
public class RedisCache {

    private Logger logger = LoggerFactory.getLogger(RedisCache.class);
    @Resource
    private RedisUtil redisUtil;
    @Autowired
    private FoodInfoService foodInfoService;
    @Autowired
    private RedisLock redisLock;

    /**
     * 锁对象
     */
    private static final Object LOCK = new Object();

    /**
     * lua脚本，先获取指定产品的秒杀数量，再递减
     */
    private static final String DESC_LUA_SCRIPT = " local remain_num = redis.call('get', KEYS[1]); "
            + " if remain_num then "
            + "     if remain_num - ARGV[1] >= 0 then return redis.call('decrby', KEYS[1], ARGV[1]); "
            + "     else return -1; end; "
            + " else return nil; end; ";

    /**
     * 使用Lua脚本来实现原子递减
     * @param key redis的key
     * @param value 需要递减的值(购买数量)
     * @param foodId 商品id
     * @return 大于0，则说明还存在库存
     */
    public Integer descValueWithLua(String key, Integer value, Integer foodId){
        if(value <= 0){
            return -1;
        }
        // lua脚本原子更新库存数量
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(DESC_LUA_SCRIPT);
        redisScript.setResultType(Long.class);
        Integer remainNum = redisUtil.execute(redisScript, Collections.singletonList(key), value);
        // 缓存不存在值，从数据库加载
        if (remainNum == null) {
            // 加锁，避免缓存没有秒杀数量时，大量访问数据库
            synchronized (LOCK) {
                // double check，实现同一个部署实例只有一个线程从数据库加载一次即可
                remainNum = getSecKillNum(foodId);
                if (remainNum == null) {
                    // 从数据库加载，如果数据库不存在，则返回-1
                    remainNum = foodInfoService.getFoodRepository(foodId);
                    if (remainNum == null) {
                        return -1;
                    }
                    // 分布式锁，避免不同机器实例的并发对Redis进行设值
                    final String lockKey = RedisLock.SECKILL_LOCK_PREFIX + foodId;
                    // 值value使用UUID生成随机值
                    final String lockValue = UUID.randomUUID().toString().replace("-", "");
                    try {
                        // 加锁
                        boolean lock = redisLock.tryLock(lockKey, lockValue, 10);
                        if (lock) {
                            // double check检查
                            remainNum = getSecKillNum(foodId);
                            if (remainNum == null) {
                                // Redis单线程特性，
                                // 此次会在后面的"递减"之前执行，
                                // 如两个实例同时执行了tryLock方法，获取分布式锁失败的实例会执行到execute部分，不过也是在此操作之后的，
                                // 故可以读取到此处的设值
                                setSecKillNum(foodId, remainNum);
                            }
                        }
                    } catch (Exception e) {
                        logger.error("redis try lock error {}", foodId, e);
                    } finally {
                        // 解锁
                        redisLock.release(lockKey, lockValue);
                    }
                }
                // 递减
                remainNum = redisUtil.execute(redisScript, Collections.singletonList(key), value);
            }
        }
        return  0;
    }

    /**
     * 设置指定产品的秒杀数量
     * @param foodId
     * @param num
     */
    public void setSecKillNum(Integer foodId, Integer num) {
        try {
            BoundValueOperations<String, Object> valueOperations = redisUtil.boundValueOperations(Constants.SEC_KILL_NUMBER_KEY_PREFIX + foodId);
            valueOperations.set(num);
        } catch (Exception e) {
            logger.error("setSecKillNum {} {}", foodId, num, e);
        }
    }

    /**
     * 获取指定产品的库存数量
     * @param foodId 商品id
     * @return 库存数量
     */
    public Integer getSecKillNum(Integer foodId) {
        try {
            return redisUtil.boundValueOps(Constants.SEC_KILL_NUMBER_KEY_PREFIX + foodId);
        } catch (Exception e) {
            logger.error("getSecKillNum {}", foodId, e);
        }
        return null;
    }
}
