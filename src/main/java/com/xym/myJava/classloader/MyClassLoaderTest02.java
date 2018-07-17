package com.xym.myJava.classloader;

/**
 * 更改当前类加载器的父加载器，跳过appClassLoader
 *
 * @author xym
 * @create 2018-07-17 17:01
 */
public class MyClassLoaderTest02 {
    public static void main(String[] args) {
        ClassLoader extClassLoader = MyClassLoaderTest02.class.getClassLoader().getParent();
        MyClassLoader myClassLoader = new MyClassLoader(extClassLoader, "d:/classloader1/");

        try {
            Class<?> aClass = myClassLoader.loadClass("com.xym.myJava.classloader.HelloWorld");
            System.out.println(aClass.getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
