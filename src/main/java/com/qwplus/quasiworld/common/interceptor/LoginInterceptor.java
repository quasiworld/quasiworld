package com.qwplus.quasiworld.common.interceptor;

import com.qwplus.quasiworld.common.response.CommonCode;
import com.qwplus.quasiworld.common.response.QWResult;
import com.qwplus.quasiworld.common.web.UserContext;
import com.qwplus.quasiworld.common.web.WebContext;
import com.qwplus.quasiworld.util.CookieUtils;
import com.qwplus.quasiworld.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: LoginInterceptor
 * @description 拦截器，所有被拦截的接口都需要经过
 * @Date 2020年12月07日 15:25:46
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Value("${COOKIE.TOKEN.KEY}")
    private String COOKIE_TOKEN_KEY;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String cookieValue = CookieUtils.getCookieValue(request, COOKIE_TOKEN_KEY);
        System.out.println(cookieValue);
        if (cookieValue == null) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().print(JsonUtils.objectToJson(QWResult.build(CommonCode.UNAUTHENTICATED)));
            return false;
        }
        UserContext user = JsonUtils.jsonToPojo(cookieValue, UserContext.class);
        request.setAttribute(WebContext.USER,user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }


}
