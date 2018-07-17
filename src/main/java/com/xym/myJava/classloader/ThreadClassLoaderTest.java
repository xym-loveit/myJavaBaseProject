package com.xym.myJava.classloader;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-07-17 21:53
 */
public class ThreadClassLoaderTest {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader());
    }
}
