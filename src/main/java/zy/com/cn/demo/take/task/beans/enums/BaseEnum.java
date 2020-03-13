package zy.com.cn.demo.take.task.beans.enums;

/**
 * 基础枚举类
 */
public interface BaseEnum<E extends Enum<?>, T> {

    public T getValue();

    public String getDisplayName();
}

