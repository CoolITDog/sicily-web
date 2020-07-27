package zy.com.cn.sicily.web.utils;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @title: SignUtil
 * @description: 签名工具
 * @author: zhangyan
 * @date: 2020-07-24 10:36
 * @version: 1.0
 **/
public class SignUtil {

    private static Logger logger = LoggerFactory.getLogger(SignUtil.class);

    public static String getSign(Object obj, String apiKey) throws Exception{
        Map<String, String> params = bean2Map(obj);
        logger.info("bean to map:{}", params);
        return getSign(params, apiKey);
    }

    /**
     * bean转map
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, String> bean2Map(Object obj) throws Exception{
        Map<String, String> params = new HashMap<String, String>(16);
        Class<?> clazz = obj.getClass();
        for(; clazz != Object.class; clazz = clazz.getSuperclass()){
            Field[] fields = clazz.getDeclaredFields();
            for(Field field:fields){
                field.setAccessible(true);
                XStreamAlias xStreamAlias = field.getDeclaredAnnotation(XStreamAlias.class);
                if(xStreamAlias == null){
                    continue;
                }
                String fieldName = field.getName();
                fieldName = fieldName.substring(0, 1).toUpperCase().concat(fieldName.substring(1));
                String getMethodName = "get".concat(fieldName);
                Method method = obj.getClass().getMethod(getMethodName);
                method.setAccessible(true);
                Object value = method.invoke(obj);
                if(value == null){
                    continue;
                }
                String key = xStreamAlias.value();
                params.put(key, value.toString());
            }
        }
        return params;
    }

    /**
     * MD5生成签名参数
     * @param params
     * @param apiKey
     * @return
     * @author Alvin
     * @date 2019年5月3日 下午8:57:21
     */
    public static String getSign(Map<String, String> params, String apiKey){
        //获取待签名字符串
        String stringSignTemp = createLinkString(params).concat("&key=").concat(apiKey);
        logger.info("link string:{}", stringSignTemp);
        return MD5Utils.md5(stringSignTemp).toUpperCase();
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {
        params = paraFilter(params);
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            //拼接时，不包括最后一个&字符
            if (i == keys.size() - 1) {
                prestr = prestr.concat(key).concat("=").concat(value);
            } else {
                prestr = prestr.concat(key).concat("=").concat(value).concat("&");
            }
        }
        return prestr;
    }
    /**
     * 除去数组中的空值和签名参数
     * @param params 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> params) {
        Map<String, String> result = new HashMap<String, String>(16);
        if (params == null || params.size() <= 0) {
            return result;
        }
        for (String key : params.keySet()) {
            String value = params.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }

    public static boolean verify(Object obj, String apiKey, String sign) throws Exception{
        return sign.equals(getSign(obj, apiKey));
    }
}
