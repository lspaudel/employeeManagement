package com.example.employeeManagement.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<CustomFilter> registerFilter(CustomFilter customFilter){
        FilterRegistrationBean<CustomFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(customFilter);
        bean.addUrlPatterns("/employee/*");
        bean.setOrder(1);
        return bean;

    }
}
