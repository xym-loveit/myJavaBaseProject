package com.xym.myJava.generics;

/**
 * 泛型方法
 */
public class Test3 {

    public static void main(String[] args) {
        Demo demo = new Demo();

        String abc = demo.fun("abc");
        Integer x = demo.fun(123);

        System.out.println(abc);
        System.out.println(x);
    }
}

class Demo {
    public <T> T fun(T t) {// 可以接收任意类型的数据
        return t;//直接返回参数
    }
}


