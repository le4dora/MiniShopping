package com.test.config;

import com.test.controller.AdministratorController;
import com.test.interceptors.CorsInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.test.interceptors.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private CorsInterceptor corsInterceptor;

    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private AdministratorController administrator;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(corsInterceptor);
        //注册和登录不拦截
        registry.addInterceptor(loginInterceptor).excludePathPatterns(
                "/user/login","/user/register",
                "/administrator/login");
    }

}
