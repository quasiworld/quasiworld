package com.qwplus.quasiworld.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: CookieUtils
 * @description CookieUtils操作工具
 * @Date 2020年12月08日 14:30:13
 */
@Component
public class CookieUtils {

    @Value("${COOKIE.DOMAIN}")
    private String DOMAIN;//cookie域名

    /**
     * 功能描述:根据名字获取cookie
     *
     * @Param: [request, name]
     * @Return: javax.servlet.http.Cookie
     * @Author: ck
     * @Date: 2020-12-10 10:14
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        if (request.getCookies() == null || request.getCookies().length == 0) {
            return null;
        }
        Map<String, Cookie> cookieMap = readCookieMap(request);
        if (cookieMap.containsKey(name)) {
            Cookie cookie = cookieMap.get(name);
            return cookie;
        } else {
            return null;
        }
    }

    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie cookieByName = getCookieByName(request, name);
        if (cookieByName == null) {
            return null;
        }
        String value = "";
        try {
            value = URLDecoder.decode(cookieByName.getValue(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value;
    }


    /**
     * 功能描述:将cookie封装到Map中
     *
     * @Param: [request]
     * @Return: java.util.Map<java.lang.String, java.lang.Object>
     * @Author: ck
     * @Date: 2020-12-10 10:28
     */
    private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

    /**
     * 功能描述:time设置为-1是为了退出浏览器cookie消失
     *
     * @Param: [request, response, name, value]
     * @Return: javax.servlet.http.HttpServletResponse
     * @Author: ck
     * @Date: 2020-12-17 11:18
     */
    public static HttpServletResponse setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value) {
        return setCookie(request, response, name, value, -1);
    }

    /**
     * 功能描述:设置cookie至浏览器中
     *
     * @Param: [response, name, value, time]
     * @Return: javax.servlet.http.HttpServletResponse
     * @Author: ck
     * @Date: 2020-12-10 11:44
     */
    public static HttpServletResponse setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, int time) {
        String encode = null;
        try {
            encode = URLEncoder.encode(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Cookie cookie = new Cookie(name, encode);
        //设置path绑定ip
        cookie.setPath("/");
        String url = request.getRequestURL().toString();
        //获取访问的域名
        String domain = getDomain(url);
        //绑定域名
        cookie.setDomain(domain);
        cookie.setMaxAge(time);
        response.addCookie(cookie);
        return response;
    }

    private static String getDomain(String url) {
        String domain = url.replace("http://", "");
        if (domain.indexOf(":") == -1) {
            //针对域名操作
            domain = domain.substring(0, domain.indexOf("/"));
            domain = domain.substring(domain.indexOf(".") + 1);
        } else {
            //针对ip操作
            System.out.println(domain);
            domain = domain.substring(0, domain.indexOf(":"));
        }
        System.out.println("domain:" + domain);
        return domain;
    }

    /**
     * 功能描述:重置cookie为不可用的状态
     *
     * @Param: [request, response, cookieName]
     * @Return: void
     * @Author: ck
     * @Date: 2020-12-21 14:12
     */
    public static void delCookieByName(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookieName.equals(cookie.getName())) {
                //设置cookie立即失效
                cookie.setMaxAge(0);
                //设置生效路径
                cookie.setPath("/");
                //设置domain的值
                cookie.setDomain(getDomain(request.getRequestURL().toString()));
                response.addCookie(cookie);
            }
        }
        //遍历cookie
    }

}
