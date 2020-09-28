package zy.com.cn.sicily.web.exception;

/**
 * @title: SnowFlakeCustomException
 * @description: 雪花值异常
 * @author: zhangyan
 * @date: 2020-09-10 15:01
 * @version: 1.0
 **/
public class SnowFlakeCustomException extends RuntimeException {

    public SnowFlakeCustomException (String message, Throwable cause) {
        super(message, cause);
    }

    public SnowFlakeCustomException(String errMsg){super(errMsg);}
}
