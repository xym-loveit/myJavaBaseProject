package com.xym.myJava.classloader;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-07-17 11:50
 */
public class Parent01 {
    static {
        System.out.println("parent init!");
    }

    public static int y = 100;
    public final static int z = 10;
}
