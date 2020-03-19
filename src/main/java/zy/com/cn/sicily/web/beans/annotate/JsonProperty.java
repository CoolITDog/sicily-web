package zy.com.cn.sicily.web.beans.annotate;

import com.fasterxml.jackson.annotation.JacksonAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @title: JsonProperty
 * @description: 属性转换注解
 * @author: zhangyan
 * @date: 2020-03-17 16:48
 * @version: 1.0
 **/
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotation
public @interface JsonProperty
{
    /**
     * Defines name of the logical property, i.e. Json object field
     * name to use for the property: if empty String (which is the
     * default), will use name of the field that is annotated.
     */
    String value() default "";
}
