package com.xym.myJava.head_first._05;

/**
 * 饿汉式单例，占用空间
 *
 * @author xym
 * @create 2019-04-10 18:00
 */
public class EagerSingleton {

    private static EagerSingleton singleton = new EagerSingleton();

    private EagerSingleton(){}

    public static EagerSingleton getInstance() {
        return singleton;
    }

    //other method
}
