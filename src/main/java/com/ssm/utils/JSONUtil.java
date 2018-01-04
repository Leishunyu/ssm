package com.ssm.utils;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * json转换类
 * @author lifei
 *
 */
public class JSONUtil {

	/**
	 * json转换成javaBean
	 * @param <T>
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static final <T> T parseObject(String json, Class<T> clazz) {
		return JSON.parseObject(json, clazz);
	}
	
	/**
     * json转换成javaBean
     * @param <T>
     * @param json
     * @param clazz
     * @return
     */
    public static final <T> List<T> parseArray(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }
	
	
	
	/**
	 * javaBean转换成json字符串
	 * @param object
	 * @return
	 */
	public static final String toJSONString(Object object) {
		return JSON.toJSONString(object);
	}
	
	/**
	 * 
	 * 功能描述: json字符串中包含类信息<br>
	 * 〈功能详细描述〉
	 *
	 * @return
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public static final String toJSONStringWithClassName(Object object){
      return JSON.toJSONString(object, SerializerFeature.WriteClassName);
//	    return null;
  }
}
