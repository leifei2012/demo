//package com.example.demo.Aop;
//
//
//import com.example.demo.servise.UserService;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//
//@Aspect
//@Component
//@Slf4j
//public class LoginInspect {
//
//    @Autowired
//    com.example.demo.servise.UserService UserService;
//
//    @Pointcut("execution(public * com.example.demo.controller.User.index(..))")
//    public void pointcut(){
//    }
//    @Around("pointcut()")
//    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
//        log.info("aop_LOGIN");
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        Cookie[] cookies=request.getCookies();
//        String value="";
//        String status="";
//        if(cookies!=null){
//            for(Cookie cookie:cookies){
//                if (cookie.getName().equals("username")) {   //Cookie:username=1qaz; JSESSIONID=5DA7EEDE4A6D1031F2A4B8A7546DEFEE; csrftoken=a7hUNZ0X7NgTNJxU1ywhpIYicYPmTl27rmQmAA258sGDwkR9jMns3WicNzj1p2Le
//                    value=cookie.getValue();
//                }
//                if (cookie.getName().equals("loginstatus")) {   //Cookie:username=1qaz; JSESSIONID=5DA7EEDE4A6D1031F2A4B8A7546DEFEE; csrftoken=a7hUNZ0X7NgTNJxU1ywhpIYicYPmTl27rmQmAA258sGDwkR9jMns3WicNzj1p2Le
//                    status=cookie.getValue();
//                }
//            }
//        }
//        log.info(value);
//        ModelAndView mv=new ModelAndView();
//        mv.setViewName("indexq");
//        Boolean user=UserService.islogin(value,status);  //对用户名及登录状态进行验证
//        if(!user){
//            mv.addObject("name","未登录");
//            return mv;
//        }
//        return joinPoint.proceed();
//    }
//}
