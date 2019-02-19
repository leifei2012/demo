package com.example.demo;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class test2 {
    @Pointcut("execution(public * com.example.demo.test.main())")
    public void pointcut(){
    }
    @Before("pointcut()")
    public void before(){
        System.out.println("111");
    }
}
