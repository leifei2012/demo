package com.example.demo;

public class test {
    String name;
    int age;

    public test (){
        this.age=21;
    }
    public test(String name,int age){
        this();
        this.name="Mick";
    }
    private void print(){
        System.out.println("最终名字="+this.name);
        System.out.println("最终的年龄="+this.age);
    }
    public static void main(String[] args) {
        test tt=new test("",0); //随便传进去的参数
        tt.print();

    }
}