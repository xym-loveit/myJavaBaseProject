package com.xym.myJava.lock;

import java.util.concurrent.TimeUnit;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-03-13 17:36
 */
public class NotifyAndNotifyAll {
    private static volatile Object resourceA = new Object();

    public static void main(String[] args) {

        Thread threadA = new Thread(() -> {
            synchronized (resourceA) {

                synchronized (resourceA) {
                    System.out.println("ThreadA get resourceA lock.");
                    try {
                        System.out.println("ThreadA begin wait");
                        resourceA.wait();
                        System.out.println("ThreadA end wait");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, "ThreadA");


        Thread threadB = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("ThreadB get resourceA lock.");
                try {
                    System.out.println("ThreadB begin wait");
                    resourceA.wait();
                    System.out.println("ThreadB end wait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "ThreadB");

        Thread threadC = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("ThreadC get resourceA lock.");

                System.out.println("ThreadC begin notify");
                //随机唤醒一个线程
                //resourceA.notify();
                resourceA.notifyAll();
                System.out.println("ThreadC end notify");
            }
        }, "ThreadC");

        threadA.start();
        threadB.start();

        try {
            TimeUnit.SECONDS.sleep(1);
            //C线程只会随机唤醒一个等待资源锁的线程
            threadC.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            threadA.join();
            threadB.join();
            threadC.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main over");

    }
}
