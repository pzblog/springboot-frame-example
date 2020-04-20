package com.example.smail;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.example.smail.mapper")
public class SmailApplication {

    public static void main(String[] args) {

        SpringApplication.run(SmailApplication.class, args);
    }
}