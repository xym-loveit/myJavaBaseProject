package com.xym.myJava.classloader;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-07-17 18:14
 */
public class MyClassLoaderTest05 {
    public static void main(String[] args) {
        BrokerDelegateClassLoader classLoader = new BrokerDelegateClassLoader("d:/classloader2/");
        try {
            Class<?> aClass = classLoader.loadClass("java.lang.String", false);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
