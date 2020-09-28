package zy.com.cn.sicily.web.utils;
/**
 * @Title: XmlConverter.java
 * @Description: xml转换器
 * @version V1.0
 */
public interface XmlConverter<T> {

	/**
	 * xml字符串转对象
	 * @param xml
	 * @return
	 * @author Alvin
	 * @date 2019年5月30日 下午2:32:37
	 */
	public T convert(String xml);
}
