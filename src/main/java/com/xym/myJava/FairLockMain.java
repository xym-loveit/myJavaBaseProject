package com.xym.myJava;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 观察输出发现，公平锁交替获取锁，非公平锁随机获取锁
 *
 * @author xym
 */
public class FairLockMain implements Runnable {

    public static void main(String[] args) {
        FairLockMain fairLockMain1 = new FairLockMain();
        FairLockMain fairLockMain2 = new FairLockMain();
        Thread t1 = new Thread(fairLockMain1);
        Thread t2 = new Thread(fairLockMain2);

        t1.start();
        t2.start();
    }

    public static ReentrantLock lock = new ReentrantLock(false);

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();

                System.out.println(Thread.currentThread().getName() + "获取锁...");

            } finally {
                lock.unlock();
            }
        }
    }
}
