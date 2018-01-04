package com.ssm.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class Digest {
    private static Logger LOGGER = LoggerFactory.getLogger(Digest.class);
    
    /**
     * 等于符号标志
     */
    public static final String EQ_SYMBOL = "=";
    
    /**
     * 并且符号标志
     */
    public static final String AND_SYMBOL = "&";


    public static String md5Digest(String s,String charset) throws UnsupportedEncodingException {
       
        try {
            MessageDigest messagedigest= MessageDigest.getInstance("MD5");
            messagedigest.update(s.getBytes(charset));
            return bytes2Hexstr(messagedigest.digest());
        } catch (Exception e) {
            LOGGER.error("错误md5Digest", e);
        }
        return "";
    }
    
    
    /**
     * 
     * 字节数组转十六进制字符串
     * 
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    public static String bytes2Hexstr(byte[] bytes) {
        String ret = "";
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;
    }
    
    
    
    private static String digest(String str){
        String md5 = null;
        try {
            LOGGER.debug("生成的字符串为:{}",str);
            md5 = md5Digest(str, "UTF-8");
            LOGGER.debug("生成的Md5摘要为:{}",md5);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return md5;
    }

    /**
     * 1.根据key对传来的map数据排序 2.生成a1=b1&a2=b2&a3=b3形式的字符串，排除某些字符串Key值 3.调用digest方法进行md5编码 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param map 要排序的字符串
     * @param key 要排序的key值
     * @return
     * @throws UnsupportedEncodingException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String digest(Map<String, String> map, String... keys){

        TreeMap<String, String> treeMap = treeMap(map, keys);
        return digest(mapToString(treeMap));
    }

    /**
     * 
     * 功能描述: <br>
     * 将map按key字符串排序的treeMap
     * 
     * @param map
     * @param keys
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static TreeMap<String, String> treeMap(Map<String, String> map, String... keys) {
        // 初始化字符串比较器
        Comparator<String> stringComparator = new StringComparator();

        TreeMap<String, String> treeMap = new TreeMap<String, String>(stringComparator);
        treeMap.putAll(map);
        // 移除非摘要的key
        for (String key : keys) {
            treeMap.remove(key);
        }
        return treeMap;
    }

    /**
     * 
     * 功能描述: <br>
     * 将map转成a1=b1&a2=b2&a3=b3形式的字符串
     * 
     * @param map
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String mapToString(Map<String, String> map) {
        StringBuilder result = new StringBuilder();
        for (Entry<String, String> entry : map.entrySet()) {
            String value = entry.getValue()==null?"": entry.getValue().trim();
            result.append(entry.getKey()).append(EQ_SYMBOL).append(value).append(AND_SYMBOL);
        }
        if (result.length() > 0) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString().trim();
    }
}
