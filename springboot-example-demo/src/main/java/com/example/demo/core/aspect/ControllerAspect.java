package com.example.demo.core.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ControllerAspect {

    @Pointcut("execution(* com.example.demo.web..*.*(..))")
    public void methodAspect(){}

    @Before("methodAspect()")
    public void before(){
        System.out.println("代理 -> 调用方法执行之前......");
    }

    @After("methodAspect()")
    public void after(){
        System.out.println("代理 -> 调用方法执行之后......");
    }


    @AfterReturning("methodAspect()")
    public void afterReturning(){
        System.out.println("代理 -> 调用方法结束之后......");
    }


    //抛出异常时才调用
    @AfterThrowing("methodAspect()")
    public void afterThrowing() {
        System.out.println("代理 -> 调用方法异常......");
    }
}
