package zy.com.cn.sicily.web.exception;

/**
 * @title: PageException
 * @description: 分页插件异常
 * @author: zhangyan
 * @date: 2020-08-10 11:10
 * @version: 1.0
 **/
public class PageException extends RuntimeException {
    public PageException() {
        super();
    }

    public PageException(String message) {
        super(message);
    }

    public PageException(String message, Throwable cause) {
        super(message, cause);
    }

    public PageException(Throwable cause) {
        super(cause);
    }
}