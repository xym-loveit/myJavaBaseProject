package com.xym.myJava.classloader;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-07-17 14:19
 */
public class Test02 {
    public static void main(String[] args) {
        try {
            Class<?> aClass = Class.forName("com.xym.myJava.classloader.TestMain");
            System.out.println(aClass.getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
