package com.xym.myJava.jvms;

/**
 * 类初始化阶段，虚拟机会保证<clinit>（类构造器）在多线程环境中会被正确加锁、同步
 * 如果多个线程同时去初始化一个类，那么只会有一个线程去执行<clinit>()方法完毕，其他线程都需要
 * 阻塞等待，直到活动线程执行<clinit>()方法完毕
 *
 * @author xym
 * @create 2019-04-_01 11:22
 */
public class InitDeadLoopClass {


    /**
     * 1、静态块在类加载的初始化阶段执行,即在类的构造函数<clinit>中执行
     * 2、静态变量的赋值也是在类加载的初始化阶段执行，即在类的构造函数<clinit>中执行
     * 3、<clinit>()构造函数是由虚拟机自动创建的
     */
    static {
        if (true) {
            System.out.println(Thread.currentThread() + " init InitDeadLoopClass");
            while (true) {

            }
        }
    }

    public static void main(String[] args) {
        Runnable script = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " start");
                InitDeadLoopClass idl = new InitDeadLoopClass();
                System.out.println(Thread.currentThread() + " run over");
            }
        };
        new Thread(script).start();
        new Thread(script).start();
    }
}
