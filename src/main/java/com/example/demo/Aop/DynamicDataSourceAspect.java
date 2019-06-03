package com.example.demo.Aop;


import com.example.demo.config.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class DynamicDataSourceAspect {

    @Pointcut("execution(* com.example.demo.servise..*.*(..))")
    private void aspect() {

    }

    @Around("aspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().getName();

        if (method.startsWith("select") || method.startsWith("query") || method
                .startsWith("search")) {
            DataSourceContextHolder.setDataSource("slaveDataSource");
            log.info("switch to slave datasource...");
        } else {
            DataSourceContextHolder.setDataSource("masterDataSource");
            log.info("switch to master datasource...");
        }
        try {
            return joinPoint.proceed();
        }finally {
            log.info("清除 datasource router...");
            DataSourceContextHolder.clear();
        }

    }

}

