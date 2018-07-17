package com.xym.myJava.classloader;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-07-17 16:24
 */
public class HelloWorld {

    static {
        System.out.println("Hello World class is initialed");
    }

    public String welcome() {
        return "Hello World";
    }

}
