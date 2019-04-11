package com.xym.myJava.head_first._05;

/**
 * 静态内部类实现单例
 *
 * @author xym
 * @create 2019-04-10 18:06
 */
public class InternalClassSingleton {

    /**
     * 防止外部实例化
     */
    private InternalClassSingleton() {
    }

    private static class SingletonHolder {
        private static final InternalClassSingleton singleton = new InternalClassSingleton();
    }

    public static InternalClassSingleton getInstance() {
        return SingletonHolder.singleton;
    }

}
