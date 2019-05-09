package com.example.demo.Aop;

import com.example.demo.Util.IpAddr;
import com.example.demo.dao.LogMapper;
import com.example.demo.entity.LogInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;


@Aspect
@Component
@Slf4j
public class Monitor {
    @Autowired
    LogMapper LogMapper;
    @Pointcut("execution(public * com.example.demo.controller.SellerOrder.*(..))")
    public void pointcut(){
    }

    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        LogInfo loginfo = new LogInfo();
        Object name=null;
        try {
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] args = joinPoint.getArgs();
            //在项目中最好记录当前操作的时间和用户
            loginfo.setClassname(className);
            loginfo.setMethodname(methodName);
            IpAddr IpAddr = new IpAddr();
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String method = request.getMethod();
            String ip = IpAddr.getIpAddr(request);
            loginfo.setIp(ip);
            loginfo.setRequestname(method);
            name = joinPoint.proceed();

        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = sdf.format(new Date());
            System.out.println("异常信息:" + e.getMessage());
            System.out.println("异常发生时间：" + time);
            log.error(joinPoint + ",耗时:" + (end - start) + " ms,抛出异常 :" + e.getMessage());
            loginfo.setExceptions(e.getMessage());
        }
        long end = System.currentTimeMillis();
        String alltime = String.valueOf(end - start);
        loginfo.setAlltime(alltime);
        loginfo.setDate(new Date());
        LogMapper.insertLog(loginfo);
        return name;
    }
}
