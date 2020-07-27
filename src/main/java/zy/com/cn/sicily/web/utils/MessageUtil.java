package zy.com.cn.sicily.web.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @title: MessageUtil
 * @description: 微信消息转码
 * @author: zhangyan
 * @date: 2020-07-24 16:25
 * @version: 1.0
 **/
public class MessageUtil {

    /**
     * @Description:解析微信发来的请求（XML）
     * @param inputStream
     * @return
     * @throws Exception
     */
    public static Map<String, String> parseXml(InputStream inputStream) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>(16);
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        @SuppressWarnings("unchecked")
        List<Element> elements = root.elements();
        // 遍历所有子节点
        for (Element e : elements){
            map.put(e.getName(), e.getText());
        }
        // 释放资源
        inputStream.close();
        inputStream = null;
        return map;
    }

    /**
     * @Description:xml字符串转Map
     * @param xml
     * @return
     * @throws Exception
     */
    public static Map<String, String> xmlToMap(String xml) throws Exception {
        InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
        return parseXml(inputStream);
    }

    /**
     * @Description: java bean转xml
     * @param obj
     * @param clazz
     * @return
     */
    public static String beanToXml(Object obj, Class<?> clazz){
        xstream = new XStream(driver);
        //使用注解
        xstream.processAnnotations(clazz);
        return xstream.toXML(obj).replaceAll("__", "_");
    }

    /**
     * xml string转java bean
     * @param xml
     * @param clazz
     * @return
     */
    public static <T> T xmlToBean(String xml, Class<T> clazz){
        return xmlToBean(xml, clazz, null);
    }
    /**
     * xml string转java bean
     * @param xml
     * @param clazz
     * @param converter 自定义转换器
     * @return
     */
    public static <T> T xmlToBean(String xml, Class<T> clazz, Converter converter){
        xstream = new XStream(driver);
        if(converter != null){
            xstream.registerConverter(converter);
        }
        xstream.processAnnotations(clazz);
        Object bean = xstream.fromXML(xml);
        if(bean != null){
            return clazz.cast(bean);
        }
        return null;
    }
    private static HierarchicalStreamDriver driver;

    private static NameCoder coder;

    private static XStream xstream;

    static {

        coder = new XmlFriendlyNameCoder("-_", "_");

        driver = new DomDriver("UTF-8", coder){

            @Override
            public HierarchicalStreamWriter createWriter(Writer out) {

                return new PrettyPrintWriter(out) {
                    // 对所有xml节点的转换都增加CDATA标记
                    boolean cdata = true;

                    @Override
                    public void startNode(String name, @SuppressWarnings("rawtypes") Class clazz) {
                        if(Number.class.isAssignableFrom(clazz)){
                            cdata = false;
                        }else{
                            cdata = true;
                        }
                        super.startNode(name, clazz);
                    }

                    @Override
                    public String encodeNode(String name) {
                        return name;
                    }

                    /**
                     * 扩展xstream，使其支持CDATA块
                     */
                    @Override
                    protected void writeText(QuickWriter writer, String text) {
                        if (cdata) {
                            writer.write("<![CDATA[");
                            writer.write(text);
                            writer.write("]]>");
                        } else {
                            writer.write(text);
                        }
                    }
                };
            }
        };
    }
}
