package com.panku.config;

import com.panku.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-17
 */
@Component
@Slf4j
public class UserLoginInterceptor implements HandlerInterceptor {

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        final RequiredLogin annotation = findAnnotation((HandlerMethod)handler, RequiredLogin.class);
        if (annotation != null)
        {
            if(userService.isAnonymousUser()){
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setHeader(HttpHeaders.CONTENT_TYPE,"application/json;charset=UTF-8");
                response.getWriter().println("{\"code\":\"1001\",\"message\":\"抱歉，请登录后重试\"}");
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    protected <T extends Annotation> T findAnnotation(final HandlerMethod handlerMethod, final Class<T> annotationType)
    {
        // Search for method level annotation
        final T annotation = handlerMethod.getMethodAnnotation(annotationType);
        if (annotation != null)
        {
            return annotation;
        }

        // Search for class level annotation
        return AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), annotationType);
    }
}
