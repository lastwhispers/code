package cn.cunchang.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginIntercepter loginIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginIntercepter)
//                .addPathPatterns("/user/address")  //  /user/**
//                .addPathPatterns("/user/infoWithJwt");

        registry.addInterceptor(loginIntercepter)
                .addPathPatterns("/user/**")   //未登录的都会被拦截
                .excludePathPatterns("/user/login");
    }
}
