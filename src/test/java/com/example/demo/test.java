package com.example.demo;

public class test {
    String name;
    static int num=0;


    private void print(){
        System.out.println("最终的年龄="+num);
        num++;
    }
    public static void main(String[] args) {
        test tt=new test(); //随便传进去的参数
        test tt1=new test();
        tt.print();
        tt1.print();

    }
}