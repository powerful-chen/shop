package com.chen.shop.sso.handler;

import com.chen.shop.common.context.ThreadContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName RequestInterceptor
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/16 8:19
 */
@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ThreadContextHolder.setHttpRequest(request);
        ThreadContextHolder.setHttpResponse(response);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        ThreadContextHolder.setHttpRequest(request);
        ThreadContextHolder.setHttpResponse(response);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //ThreadLocal 线程变量隔离,会在每一个线程里面创建一个副本对象,每个线程互不影响
        //但是如果用完不移除,会有内存溢出的风险
        //Thread中,ThreadLocalMap -> ThreadLocal key ,value存入的值,ThreadLocal弱引用
        ThreadContextHolder.remove();
    }
}
