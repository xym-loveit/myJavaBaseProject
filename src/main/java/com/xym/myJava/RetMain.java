package com.xym.myJava;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁，加锁和释放所要成对出现，加锁N次必须释放N次
 *
 * @author xym
 */
public class RetMain {

    public static class MyThread implements Runnable {
        static ReentrantLock lock = new ReentrantLock();

        static int count = 0;

        /**
         * 可重入锁
         */
        @Override
        public void run() {
            try {
                lock.lock();
                lock.lock();
                for (int i = 0; i < 1000000; i++) {
                    count++;
                }
            } finally {
                lock.unlock();
                lock.unlock();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        Thread t1 = new Thread(thread);
        Thread t2 = new Thread(thread);
        t1.start();
        t2.start();

        t1.join();
        t2.join();


        System.out.println(MyThread.count);
    }
}
