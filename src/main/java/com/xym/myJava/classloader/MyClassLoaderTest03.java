package com.xym.myJava.classloader;

/**
 * 将当前类加载器的父类加载器置空，使其无法加载，被迫最后由当前类加载器加载
 *
 * @author xym
 * @create 2018-07-17 17:01
 */
public class MyClassLoaderTest03 {
    public static void main(String[] args) {
        MyClassLoader myClassLoader = new MyClassLoader(null, "d:/classloader1/");

        try {
            Class<?> aClass = myClassLoader.loadClass("com.xym.myJava.classloader.HelloWorld");
            System.out.println(aClass.getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
