package zy.com.cn.sicily.web.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @Title: AbstractConverter.java
 * @Description: 转换器抽象
 * @version V1.0
 */
@SuppressWarnings("unchecked")
public abstract class AbstractXmlConverter<T> implements XmlConverter<T> {

	/**
	 * 解码
	 * @param response
	 * @param xmlData
	 * @return
	 * @author Alvin
	 * @date 2019年5月30日 下午3:31:57
	 */
	public abstract T unmarshal(T response, Map<String, String> xmlData);
	
	@Override
	public T convert(String xml) {
		return unmarshal(xml);
	}
	
	/**
	 * xml转map
	 * @param xml
	 * @return
	 * @throws Exception
	 * @author Alvin
	 * @date 2019年5月30日 下午3:32:29
	 */
	private Map<String, String> xmlConverterToMap(String xml) throws Exception {
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>(32);
		// 读取输入流
		SAXReader reader = new SAXReader();
		InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
		Document document = reader.read(inputStream);
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elements = root.elements();
		// 遍历所有子节点
		for (Element e : elements) {
			map.put(e.getName(), e.getText());
		}
		// 释放资源
		inputStream.close();
		inputStream = null;
		return map;
	}
	
	/**
	 * xml解码成java类
	 * @param xml
	 * @return
	 * @author Alvin
	 * @date 2019年5月30日 下午3:32:48
	 */
	private T unmarshal(String xml){
		try{
			Map<String, String> xmlData = xmlConverterToMap(xml);
			Class<T> clazz = (Class<T>) getTClass();
			T response = clazz.newInstance();
			for(; clazz != Object.class; clazz = (Class<T>) clazz.getSuperclass()){
				Field[] fields = clazz.getDeclaredFields();
				for(Field field:fields){
					field.setAccessible(true);
					XStreamAlias alias = field.getAnnotation(XStreamAlias.class);
					if(alias == null){
						continue;
					}
					String value = xmlData.get(alias.value());
					Method method = clazz.getMethod(setterName(field.getName()), field.getType());
					if(method == null || value == null){
						continue;
					}
					method.invoke(response, valueOf(field.getType(), xmlData.get(alias.value())));
				}
			}
			response = unmarshal(response, xmlData);
			return response;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取set方法名
	 * @param fieldName
	 * @return
	 * @author Alvin.zengqi  
	 * @date 2018年2月6日 下午6:43:32
	 */
	private String setterName(String fieldName){
		String firstChar = fieldName.substring(0, 1);
		fieldName = fieldName.replaceFirst(firstChar, firstChar.toUpperCase());
		return "set".concat(fieldName);
	}
	
	private Object valueOf(Class<?> clazz, String object){
		if(clazz.isPrimitive()){
			if(double.class == clazz){
				return Double.valueOf(object).doubleValue();
			}
			if(float.class == clazz){
				return Float.valueOf(object).floatValue();
			}
			if(long.class == clazz){
				return Long.valueOf(object).longValue();
			}
			if(int.class == clazz){
				return Integer.valueOf(object).intValue();
			}
		}
		if(Double.class.isAssignableFrom(clazz)){
			return Double.valueOf(object);
		}
		if(Float.class.isAssignableFrom(clazz)){
			return Float.valueOf(object);
		}
		if(Long.class.isAssignableFrom(clazz)){
			return Long.valueOf(object);
		}
		if(Integer.class.isAssignableFrom(clazz)){
			return Integer.valueOf(object);
		}
		return object;
	}
	
	private Class<T> getTClass()
    {
        Class<T> tClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }
}
