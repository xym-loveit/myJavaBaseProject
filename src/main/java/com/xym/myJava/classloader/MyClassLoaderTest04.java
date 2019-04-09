package com.xym.myJava.classloader;

/**
 * 将当前类加载器的父类加载器置空，使其无法加载，被迫最后由当前类加载器加载
 *
 * @author xym
 * @create 2018-07-17 17:_01
 */
public class MyClassLoaderTest04 {
    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader classLoader1 = new MyClassLoader(null, "d:/classloader1/");
        BrokerDelegateClassLoader classLoader2 = new BrokerDelegateClassLoader(null, "d:/classloader1/");


        /**
         * 不同的类加载器可以加载同一个类
         */
        Class<?> aClass = classLoader1.loadClass("com.xym.myJava.classloader.HelloWorld");
        Class<?> bClass = classLoader2.loadClass("com.xym.myJava.classloader.HelloWorld");
        System.out.println(aClass.getClassLoader());
        System.out.println(bClass.getClassLoader());
        System.out.println(aClass.hashCode());
        System.out.println(bClass.hashCode());
        System.out.println(aClass == bClass);


        /**
         * 不同的类加载器可以加载同一个类
         */
        MyClassLoader myClassLoader3 = new MyClassLoader(null, "d:/classloader1/");
        MyClassLoader myClassLoader4 = new MyClassLoader(null, "d:/classloader1/");
        Class<?> cClass = myClassLoader3.loadClass("com.xym.myJava.classloader.HelloWorld");
        Class<?> dClass = myClassLoader4.loadClass("com.xym.myJava.classloader.HelloWorld");
        System.out.println(cClass.getClassLoader());
        System.out.println(dClass.getClassLoader());
        System.out.println(dClass.hashCode());
        System.out.println(cClass.hashCode());
        System.out.println(cClass == dClass);
    }
}
