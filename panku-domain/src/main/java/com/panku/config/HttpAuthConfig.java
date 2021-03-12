package com.panku.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description:
 * @author: uaike
 * @create: 2021-03-12
 */
@Configuration
public class HttpAuthConfig implements WebMvcConfigurer {

    @Bean
    public AuthorizationInterceptor getAuthorizationInterceptor(){
        return new AuthorizationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAuthorizationInterceptor());
    }
}
