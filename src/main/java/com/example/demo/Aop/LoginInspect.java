package com.example.demo.Aop;


import com.example.demo.servise.UserService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Aspect
@Component
@Slf4j
public class LoginInspect {

    @Autowired
    com.example.demo.servise.UserService UserService;

    @Pointcut("execution(public * com.example.demo.controller.SellerOrder.*(..))")
    public void pointcut(){
    }
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("aop_LOGIN");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session=request.getSession();
        if(null==session.getAttribute("username")){    //通过session判断是否登录
            ModelAndView mv=new ModelAndView();
            mv.setViewName("indexq");
            mv.addObject("name","未登录");
            return mv;
        }
        return joinPoint.proceed();
    }
}
