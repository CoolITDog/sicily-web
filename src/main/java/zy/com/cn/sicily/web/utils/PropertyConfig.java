package zy.com.cn.sicily.web.utils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Title: PropertyConfig.java
 * @Description: 配置工具类
 * @version V1.0
 * @date 2020年9月4日11:03:09
 */
public class PropertyConfig{
	
	private static final Map<String, String> wellKnownSimplePrefixes = new HashMap<String, String>(4);
	private static final String DEFAULT_PLACEHOLDER_PREFIX = "${";
	private static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";
	private static final String DEFAULT_VALUE_SEPARATOR = ":";
	private static String simplePrefix;
	
	static {
		wellKnownSimplePrefixes.put("}", "{");
		wellKnownSimplePrefixes.put("]", "[");
		wellKnownSimplePrefixes.put(")", "(");
		
		String simplePrefixForSuffix = (String) wellKnownSimplePrefixes.get(DEFAULT_PLACEHOLDER_SUFFIX);
		if ((simplePrefixForSuffix != null) && (DEFAULT_PLACEHOLDER_PREFIX.endsWith(simplePrefixForSuffix))) {
			simplePrefix = simplePrefixForSuffix;
		} else {
			simplePrefix = DEFAULT_PLACEHOLDER_PREFIX;
		}
	}
	
	public static void main(String [] args){
		Properties defaultProps = new Properties();
		defaultProps.put("host", "https://www.glsx.com.cn");
		defaultProps.put("port", "80");
		defaultProps.put("domain", "${host}:${port}");
		defaultProps.put("url", "${domain}/index?r={0}");
		System.out.println(getExtendString(defaultProps,"url", new Object[]{String.valueOf(System.currentTimeMillis())}));
	}
	
	/**
	 * @Description: 获取字符串配置（支持占位符${}, 配置继承）
	 * @param key
	 * @return
	 */
	public static String getExtendString(Properties defaultProps,String key) {
		String value = getString(defaultProps,key);
		if(value != null){
			value = replacePlaceholders(defaultProps,value);
		}
		return value;
	}
	
	public static String getString(Properties defaultProps,String key){
		Object object = defaultProps.getProperty(key);
		if(null != object) {
			return (String)object;
		}else {
			return null;
		}
	}
	
	/**
	 * @Description: 获取字符串配置（支持占位符${}, 配置继承）
	 * @param key
	 * @return
	 */
	public static String getExtendString(Properties defaultProps,String key, Object[] array) {
		String value = getExtendString(defaultProps,key);
		if(value != null){
			value = MessageFormat.format(value, array);
		}
		return value;
	}
	
	private static String replacePlaceholders(Properties defaultProps,String value) {
		StringBuilder result = new StringBuilder(value);
		int startIndex = value.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
		while (startIndex != -1) {
			int endIndex = findPlaceholderEndIndex(result, startIndex);
			if (endIndex != -1) {
				String placeholder = result.substring(startIndex + DEFAULT_PLACEHOLDER_PREFIX.length(), endIndex);
				String propVal = getExtendString(defaultProps,placeholder);
				if ((propVal == null) && (DEFAULT_VALUE_SEPARATOR != null)) {
					int separatorIndex = placeholder.indexOf(DEFAULT_VALUE_SEPARATOR);
					if (separatorIndex != -1) {
						String actualPlaceholder = placeholder.substring(0, separatorIndex);
						String defaultValue = placeholder.substring(separatorIndex + DEFAULT_VALUE_SEPARATOR.length());
						propVal = getExtendString(defaultProps,actualPlaceholder);
						if (propVal == null) {
							propVal = defaultValue;
						}
					}
				}
				if (propVal != null) {
					result.replace(startIndex, endIndex + DEFAULT_PLACEHOLDER_SUFFIX.length(), propVal);
					startIndex = result.indexOf(DEFAULT_PLACEHOLDER_PREFIX, startIndex + propVal.length());
				}else {
					throw new IllegalArgumentException(new StringBuilder().append("Could not resolve placeholder '").append(placeholder).append("'").append(" in string value \"")
							.append(value).append("\"").toString());
				}
			} else {
				startIndex = -1;
			}
		}
		return result.toString();
	}
	
	private static int findPlaceholderEndIndex(CharSequence buf, int startIndex) {
		int index = startIndex + DEFAULT_PLACEHOLDER_PREFIX.length();
		int withinNestedPlaceholder = 0;
		while (index < buf.length()) {
			if (substringMatch(buf, index, DEFAULT_PLACEHOLDER_SUFFIX)) {
				if (withinNestedPlaceholder > 0) {
					--withinNestedPlaceholder;
					index += DEFAULT_PLACEHOLDER_SUFFIX.length();
				}
				return index;
			}
			if (substringMatch(buf, index, simplePrefix)) {
				++withinNestedPlaceholder;
				index += simplePrefix.length();
			}
			++index;
		}
		return -1;
	}
	
	public static boolean substringMatch(CharSequence str, int index, CharSequence substring) {
		for (int j = 0; j < substring.length(); ++j) {
			int i = index + j;
			if ((i >= str.length()) || (str.charAt(i) != substring.charAt(j))) { return false; }
		}
		return true;
	}
	
}
