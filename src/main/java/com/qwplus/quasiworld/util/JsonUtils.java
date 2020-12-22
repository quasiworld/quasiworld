package com.qwplus.quasiworld.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * @ClassName: JsonUtils
 * @description json处理工具
 * @Date 2020年12月17日 16:40:47
 */
public class JsonUtils {

    private static  final ObjectMapper MAPPER =  new ObjectMapper();

    /**
     * 功能描述:将对象转换成json字符串
     * @Param: [data]
     * @Return: java.lang.String
     * @Author: ck
     * @Date: 2020-12-17 16:43
     */
    public static String objectToJson(Object data){
        try{
            String string = MAPPER.writeValueAsString(data);
            return string;
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 功能描述:将json转换为pojo
     * @Param: [jsonData, beanType]
     * @Return: T
     * @Author: ck
     * @Date: 2020-12-17 16:47
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType){
        try{
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能描述:将json转换为list
     * @Param: [jsonData, beanType]
     * @Return: java.util.List<T>
     * @Author: ck
     * @Date: 2020-12-17 16:48
     */
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            List<T> list = MAPPER.readValue(jsonData, javaType);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
