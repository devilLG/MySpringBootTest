package com.zhilai.master.modules.utils;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * fastjson工具类
 * @author yuanwei
 * @date 2017-09-21
 */
public class FastJsonTools {

    private static final SerializeConfig config;

    static {
        config = new SerializeConfig();
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
    }

    private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
    };
    

    public static String toJSONString(Object object) {
        return JSON.toJSONString(object, config, features);
    }
    
    public static String toJSONNoFeatures(Object object) {
        return JSON.toJSONString(object, config);
    }
    


    public static Object toBean(String text) {
        return JSON.parse(text);
    }

    public static <T> T toBean(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }
    
        
    public static void main(String[] args) {
    	String body="[{\"login_id\":\"yuanwei\",\"password\":\"123456\",\"encryption\":\"1\",\"encrypt_type\":\"00\",\"salt\":\"2017-3-24 17:00:00\",\"r_state\":\"00\",\"person_info\":\"operator\",\"role_name\":\"admin\"}]";
		String content="{\"ZHEAD\":{\"bcode\":\"01\",\"tcode\":\"1026\",\"iend\":\"3\",\"iflag\":\"1\",\"istart\":\"1\"},\"ZBODY\":{\"auth_name\":\"testName\",\"auth_id\":\"test123\",\"site_name\":\"site6606\",\"site_id\":\"6606\",\"msg\":"+body+"}}";
		  
		XmlJson xmljson=toBean(content,XmlJson.class);
		if(null!=xmljson){
			//System.out.println(xmljson.getZmsg());
		}
		
	}
    
   
    // 转换为数组
    public static <T> Object[] toArray(String text) {
        return toArray(text, null);
    }

    // 转换为数组
    public static <T> Object[] toArray(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz).toArray();
    }

    // 转换为List
    public static <T> List<T> toList(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }

//    /**
//     * 将javabean转化为序列化的json字符串
//     * @param keyvalue
//     * @return
//     */
//    public static Object beanToJson(KeyValue keyvalue) {
//        String textJson = JSON.toJSONString(keyvalue);
//        Object objectJson  = JSON.parse(textJson);
//        return objectJson;
//    }
    
    /**
     * 将string转化为序列化的json字符串
     * @param keyvalue
     * @return
     */
    public static Object textToJson(String text) {
        Object objectJson  = JSON.parse(text);
        return objectJson;
    }
    
    /**
     * json字符串转化为map
     * @param s
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static Map stringToCollect(String s) {
        Map m = JSONObject.parseObject(s);
        return m;
    }
    
    /**
     * 将map转化为string
     * @param m
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static String collectToString(Map m) {
        String s = JSONObject.toJSONString(m);
        return s;
    }
    
    
}
