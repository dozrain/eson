package com.rain.config.filter;


import com.rain.utils.framework.context.AppContext;
import com.rain.utils.framework.context.ContextUtil;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Created by zhangxl on 2015/8/24.
 */
@Component
public class AmroContextResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(AppContext.class);
    }


    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (methodParameter.getParameterType().equals(AppContext.class)) {
            AppContext context = ContextUtil.getAppContext();
            return context;
        }else {
            return null;
        }
    }
}
