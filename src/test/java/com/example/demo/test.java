package com.example.demo;


import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    String name;
    static int num=0;


    private void print(){
        System.out.println("最终的年龄="+num);
        num++;
    }
    public static void main(String[] args) throws Exception {
//        System.out.println(DigestUtils.md5DigestAsHex("1qaz".getBytes()).toUpperCase());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date start= df.parse("2019-05-06 00:00:00");
        System.out.println(start);
        System.out.println(new Date());
    }
}