package zy.com.cn.sicily.web.beans.annotate;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @title: ReflectMethodName
 * @description: 反射方法名注解设置
 * @author: zhangyan
 * @date: 2020-08-10 10:59
 * @version: 1.0
 **/
@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReflectMethodName {

    /**
     * @Description: get方法名
     * @return
     * @author Alvin.zengqi
     * @date 2018年1月12日 下午3:33:22
     */
    public abstract String get() default "";

    /**
     * @Description: set方法名
     * @return
     * @author Alvin.zengqi
     * @date 2018年1月12日 下午3:33:36
     */
    public abstract String set() default "";
}
