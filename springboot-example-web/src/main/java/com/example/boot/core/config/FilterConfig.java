package com.example.boot.core.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.boot.core.filter.LoginFilter;

@Configuration
public class FilterConfig {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
    public FilterRegistrationBean helloFilterRegistrationBean() {
    	LoginFilter otherFilter = new LoginFilter();
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("loginFilter");
        registrationBean.setFilter(otherFilter);
        registrationBean.setOrder(1);
        // 没有配置setUrlPatterns，则是全部拦截
        return registrationBean;
    }

}
