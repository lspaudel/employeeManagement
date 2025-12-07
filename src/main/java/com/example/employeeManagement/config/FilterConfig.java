package com.example.employeeManagement.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<MDCFilter> mdcFilter(MDCFilter mdcFilter){
        FilterRegistrationBean<MDCFilter> bean =  new FilterRegistrationBean<>();
        bean.setName("mdcFilter");
        bean.setFilter(mdcFilter);
        bean.addUrlPatterns("/*");
        bean.setOrder(1);
        System.out.println("MDC filter is working");
        return bean;
    }
    @Bean
    public FilterRegistrationBean<ApiKeyFilter> registerApiKeyFilter(ApiKeyFilter apiKeyFilter){
        FilterRegistrationBean<ApiKeyFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(apiKeyFilter);
        bean.addUrlPatterns("/employee/*");
        bean.setOrder(2);
        return bean;
    }
    @Bean
    FilterRegistrationBean<RequestTimeFilter> registerTimingFilter(RequestTimeFilter requestTimeFilter){
        FilterRegistrationBean <RequestTimeFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(requestTimeFilter);
        bean.addUrlPatterns("/employee/*");
        bean.setOrder(3);
        return bean;
    }
}
