package com.example.boot.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

@EnableTransactionManagement
@Configuration
@MapperScan("com.phxl.**.dao*")
public class MybatisPlusConfig {

    @Primary
    @Bean
    public PaginationInterceptor beanPaginationInterceptor() {
        PaginationInterceptor pageInterceptor = new PaginationInterceptor();
        return pageInterceptor;
    }


}
