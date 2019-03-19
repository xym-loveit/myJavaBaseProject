package com.xym.myJava.thread;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-03-18 23:35
 */
public class TestYieldDemo {
    public static final Object LOCK = new Object();
    public static final AtomicBoolean FLAG = new AtomicBoolean(false);

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (LOCK) {
                System.out.println(Thread.currentThread().getName() + "准备yield");
                if (!FLAG.get()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "yield完毕");
            }
        }, "ThreadA").start();


        new Thread(() -> {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (LOCK) {
                System.out.println("正常执行！");
                FLAG.set(Boolean.TRUE);
            }
        }, "ThreadB").start();
    }
}
