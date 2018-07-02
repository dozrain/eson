package com.rain.config.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ron
 *         2016/5/26.
 */
@Component
public class AogInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(AogInterceptor.class);


    @Lazy
    private static ThreadLocal<Long> THREAD_LOCAL_TIME = new ThreadLocal<>();


    ;@Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //覆盖 request 为 spring 封装的request

        THREAD_LOCAL_TIME.set(System.currentTimeMillis());

        Method method = ((HandlerMethod)handler).getMethod();
        Map map = new HashMap(request.getParameterMap());
        return true;
    }



    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView model) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return;
        }
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (handler instanceof HandlerMethod) {
            Long startTime = THREAD_LOCAL_TIME.get();
            THREAD_LOCAL_TIME.remove(); // servlet 容器使用线程池处理请求，线程会重用，需要清理
            if (startTime == null) { // 同一个线程进入多次, startTime has been removed
                startTime = System.currentTimeMillis();
            }
        }
    }
}
