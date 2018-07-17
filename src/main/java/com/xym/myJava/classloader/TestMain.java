package com.xym.myJava.classloader;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-07-17 14:03
 */
public class TestMain {
    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        for (String s : System.getProperty("sun.boot.class.path").split(";")) {
            System.out.println(s);
        }

        for (String s : System.getProperty("java.ext.dirs").split(";")) {
            System.out.println(s);
        }
    }
}
