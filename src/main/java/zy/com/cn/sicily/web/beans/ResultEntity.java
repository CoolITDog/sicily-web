package zy.com.cn.sicily.web.beans;

import java.io.Serializable;

/**
 * @title: ResultEntity
 * @description: 请求结果实体对象
 * @author: zhangyan
 * @date: 2020-03-12 15:12
 * @version: 1.0
 **/
public class ResultEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功
     */
    public static final String SUCCESS = "0";

    /**
     * 失败
     */
    public static final String ERROR = "-1";
    /**
     * 返回编码(0：成功；-1：失败)
     */
    private String returnCode = SUCCESS;
    /**
     * 附加信息
     */
    private String message;
    /**
     * 错误编码
     */
    private String errId;
    /**
     * 结果数据
     */
    private T data;

    /**
     * @Description: 返回成功结果
     * @param data 结果数据
     * @return
     * @author Alvin.zengqi
     * @date 2018年1月15日 下午12:29:01
     */
    public static <T> ResultEntity<T> success(T data){
        return success(data, null);
    }

    /**
     * @Description: 返回成功结果
     * @param data 结果数据
     * @param message 附加信息
     * @return
     * @author Alvin.zengqi
     * @date 2018年1月15日 下午12:30:47
     */
    public static <T> ResultEntity<T> success(T data, String message){
        return ok(data, message);
    }

    /**
     * @Description: 返回成功结果
     * @param data 结果数据
     * @return
     * @author Alvin.zengqi
     * @date 2018年1月15日 下午12:29:01
     */
    public static <T> ResultEntity<T> ok(T data){
        return ok(data, null);
    }

    /**
     * @Description: 返回成功结果
     * @param data 结果数据
     * @param message 附加信息
     * @return
     * @author Alvin.zengqi
     * @date 2018年1月15日 下午12:30:47
     */
    public static <T> ResultEntity<T> ok(T data, String message){
        ResultEntity<T> result = new ResultEntity<T>();
        result.setData(data);
        result.setMessage(message);
        result.setReturnCode(SUCCESS);
        return result;
    }

    /**
     * @Description: 返回错误结果
     * @param errId 错误编码
     * @return
     * @author Alvin.zengqi
     * @date 2018年1月15日 下午12:31:28
     */
    public static <T> ResultEntity<T> error(String errId){
        return error(errId, null, null);
    }

    /**
     * @Description: 返回错误结果
     * @param errId 错误编码
     * @param message 错误信息
     * @return
     * @author Alvin.zengqi
     * @date 2018年1月15日 下午12:31:44
     */
    public static <T> ResultEntity<T> error(String errId, String message){
        return error(errId, message, null);
    }

    /**
     * @Description: 返回错误结果
     * @param errId 错误编码
     * @param message 错误信息
     * @param data 附加数据
     * @return
     * @author Alvin.zengqi
     * @date 2018年1月15日 下午12:31:49
     */
    public static <T> ResultEntity<T> error(String errId, String message, T data){
        ResultEntity<T> result = new ResultEntity<T>();
        result.setErrId(errId);
        result.setMessage(message);
        result.setData(data);
        result.setReturnCode(ERROR);
        return result;
    }

    public static String getSUCCESS() {
        return SUCCESS;
    }

    public static String getERROR() {
        return ERROR;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrId() {
        return errId;
    }

    public void setErrId(String errId) {
        this.errId = errId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "returnCode='" + returnCode + '\'' +
                ", message='" + message + '\'' +
                ", errId='" + errId + '\'' +
                ", data=" + data +
                '}';
    }
}
