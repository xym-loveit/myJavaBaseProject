package com.xym.myJava;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-10-12 16:42
 */
public class ThreadLockMain {
    public static void main(String[] args) {

        new Thread(new MyRunnable(), "myThread").start();

    }
}

class MyRunnable implements Runnable {

    @Override
    public synchronized void run() {
        method1();
        System.out.println("run方法在执行");
    }

    private synchronized void method1() {
        System.out.println("普通加锁方法！！！");
    }
}
