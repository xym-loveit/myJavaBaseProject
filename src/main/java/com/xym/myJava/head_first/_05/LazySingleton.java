package com.xym.myJava.head_first._05;

/**
 * 懒汉式
 *
 * @author xym
 * @create 2019-04-10 17:58
 */
public class LazySingleton {

    private static LazySingleton singleton;

    private LazySingleton(){}

    //多线程安全问题
    public static LazySingleton getInstance() {
        if (singleton == null) {
            singleton = new LazySingleton();
        }
        return singleton;
    }

}
