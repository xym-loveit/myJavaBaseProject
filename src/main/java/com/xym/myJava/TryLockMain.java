package com.xym.myJava;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过trylock 循环尝试获取锁，避免了死锁发生
 *
 * @author xym
 */
public class TryLockMain implements Runnable {

    public static void main(String[] args) {
        TryLockMain tryLockMain1 = new TryLockMain(1);
        TryLockMain tryLockMain2 = new TryLockMain(2);
        Thread thread1 = new Thread(tryLockMain1);
        Thread thread2 = new Thread(tryLockMain2);


        thread1.start();
        thread2.start();
    }

    /**
     * 产生两把可重入锁
     */
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    int lock;

    public TryLockMain(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {

        if (lock == 1) {
            while (true) {
                System.out.println("尝试一下1111-----");
                if (lock1.tryLock()) {
                    try {

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if (lock2.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getId() + ":My job done");
                                return;
                            } finally {
                                lock2.unlock();
                            }
                        }
                    } finally {
                        lock1.unlock();
                    }
                }
            }
        } else {
            while (true) {
                System.out.println("尝试一下2222-----");
                if (lock2.tryLock()) {
                    try {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (lock1.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getId() + ":My job done");
                                return;
                            } finally {
                                lock1.unlock();
                            }
                        }

                    } finally {
                        lock2.unlock();
                    }
                }
            }
        }

    }
}
