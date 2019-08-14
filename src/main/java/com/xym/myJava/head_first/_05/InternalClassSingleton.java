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

    //jvm保证的该类只有用到时候才会加载并且实例化
    private static class SingletonHolder {
        private static final InternalClassSingleton singleton = new InternalClassSingleton();
    }

    public static InternalClassSingleton getInstance() {
        return SingletonHolder.singleton;
    }



}
