package com.xym.myJava;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁的所中断特性，有效防止了死锁
 *
 * @author xym
 */
public class IntLockMain implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        IntLockMain intLockMain1 = new IntLockMain(1);
        IntLockMain intLockMain2 = new IntLockMain(2);
        Thread t1 = new Thread(intLockMain1, "t1");
        Thread t2 = new Thread(intLockMain2, "t2");

        t1.start();
        t2.start();

        /**
         * 主线程等待1秒，等待子线程被死锁
         */
        Thread.sleep(1000);

        /**
         * 中断其中一个线程,t2线程妥协后t1顺利执行完毕
         */
        t2.interrupt();
    }

    /**
     * 2把可重入锁
     */
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    int lock;

    /**
     * 控制加锁顺序，方便构造死锁
     *
     * @param lock
     */
    public IntLockMain(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {

        try {
            if (lock == 1) {
                /**
                 * 可响应中断的锁
                 */
                lock1.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {

                }

                /**
                 * 可响应中断的锁
                 */
                lock2.lockInterruptibly();
                System.out.println(Thread.currentThread().getName() + "----获取所继续执行");
            } else {
                lock2.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {

                }
                lock1.lockInterruptibly();
                System.out.println(Thread.currentThread().getName() + "----获取所继续执行");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            /**
             *当前线程是否持有指定的锁
             */
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }

            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }

            System.out.println(Thread.currentThread().getId() + "：线程退出");
        }

    }
}
