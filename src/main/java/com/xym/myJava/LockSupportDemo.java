package com.xym.myJava;

import java.util.concurrent.locks.LockSupport;

/**
 * 可以在线程内的任意位置让线程阻塞，它不会发生让线程无法继续执行的情况
 * 和wait相比，也不需要先获得某个对象的锁，也不会发生InterruptedException异常
 *
 * @author xym
 */
public class LockSupportDemo {
    public static Object u = new Object();

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(100);
        t2.start();
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);
        t1.join();
        t2.join();
    }

    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (u) {
                System.out.println("in " + getName());
                LockSupport.park();
                System.out.println("after park...");
            }
        }
    }
}
