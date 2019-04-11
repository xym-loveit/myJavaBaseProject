package com.xym.myJava.head_first._05;

/**
 * 双重检查+锁
 *
 * @author xym
 * @create 2019-04-10 18:02
 */
public class DoubleCheckSingleton {

    //注意这里的修饰为volatile，保证多线程下的可见性
    private static volatile DoubleCheckSingleton singleton;

    //所有的单例模式，都需要将构造函数私有化
    private DoubleCheckSingleton() {
    }

    public static DoubleCheckSingleton getInstance() {
        if (singleton == null) {
            //注意这里的同步代码块
            synchronized (DoubleCheckSingleton.class) {
                singleton = new DoubleCheckSingleton();
            }
        }
        return singleton;
    }

    //other method
}
