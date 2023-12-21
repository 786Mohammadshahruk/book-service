package com.book.service.books.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
        System.out.println("addInterceptors");
        //registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/order/**");
    }
}