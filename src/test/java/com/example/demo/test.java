package com.example.demo;


import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.DigestUtils;

public class test {
    String name;
    static int num=0;


    private void print(){
        System.out.println("最终的年龄="+num);
        num++;
    }
    public static void main(String[] args) {
        System.out.println(DigestUtils.md5DigestAsHex("1qaz".getBytes()).toUpperCase());
    }
}