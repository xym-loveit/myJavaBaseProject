package com.xym.myJava.classloader;

import java.lang.reflect.Method;

/**
 * 自定义类加载器
 *
 * @author xym
 * @create 2018-07-17 16:08
 */
public class MyClassLoaderTest {
    public static void main(String[] args) {
        MyClassLoader myClassLoader = new MyClassLoader();
        try {
            //加载并不会导致类的初始化
            Class<?> aClass = myClassLoader.loadClass("com.xym.myJava.classloader.HelloWorld");
            System.out.println(aClass.getClassLoader());


            Object o = aClass.newInstance();
            System.out.println(o);
            Method welcome = aClass.getMethod("welcome");
            String ret = (String) welcome.invoke(o);
            System.out.println("result=" + ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
