package com.qwplus.quasiworld.common.web;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: WebContext
 * @description 上下文工具类
 * @Date 2020年12月11日 10:50:40
 */
public class WebContext {

    public static final String USER = "user";

    /**
     * 功能描述:获取HttpServletRequest请求
     * @Param: []
     * @Return: javax.servlet.http.HttpServletRequest
     * @Author: ck
     * @Date: 2020-12-11 10:59
     */
    public static HttpServletRequest getRequest(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    /**
     * 功能描述:获取HttpServletResponse响应
     * @Param: []
     * @Return: javax.servlet.http.HttpServletResponse
     * @Author: ck
     * @Date: 2020-12-11 11:00
     */
    public static HttpServletResponse getResponse(){
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        //设置json的返回格式
        response.setHeader("Content-type", "json/application;charset=UTF-8");
        //设置返回字符集
        response.setCharacterEncoding("UTF-8");
        return response;
    }

    public static UserContext getUser(){
        return (UserContext)getRequest().getAttribute(USER);
    }

    /**
     * 功能描述:从请求中获取数据 例：?name=1
     * @Param: [name]
     * @Return: java.lang.String
     * @Author: ck
     * @Date: 2020-12-17 10:17
     */
    public static String getParameter(String name){
        String parameter = getRequest().getParameter(name);
        return parameter;
    }



}
