package com.xym.myJava;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 对比可重入锁和读写锁的执行性能
 * <p></p>
 * 特别是在读多写少的情况下,性能对比更为明显
 *
 * @author xym
 */
public class ReadWriteLockDemo {

    /**
     * 普通重入锁
     */
    private static Lock lock = new ReentrantLock();

    private int value;

    /**
     * 读写锁性能锁
     */
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    public Object handleRead(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            /**
             * 模拟读操作
             *
             * 读操作的耗时越多，读写锁的优势就越明显
             */
            Thread.sleep(1000);
            return value;
        } finally {
            lock.unlock();
        }
    }

    public void handleWrite(Lock lock, int index) throws InterruptedException {
        try {
            lock.lock();
            /**
             * 模拟写操作
             *
             */
            Thread.sleep(1000);
            value = index;
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();

        Thread[] rThreads = new Thread[18];
        Thread[] wThreads = new Thread[2];

        Runnable readThread = new Runnable() {
            @Override
            public void run() {
                try {
//                    readWriteLockDemo.handleRead(readLock);
                    readWriteLockDemo.handleRead(lock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable writeThread = new Runnable() {
            @Override
            public void run() {
                try {
//                    readWriteLockDemo.handleWrite(writeLock, new Random().nextInt());
                    readWriteLockDemo.handleWrite(lock, new Random().nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        long startTime = System.currentTimeMillis();


        /**
         *模拟读多写少的场景
         */
        for (int i = 0; i < 18; i++) {
            rThreads[i] = new Thread(readThread);
            rThreads[i].start();
        }

        for (int i = 0; i < 2; i++) {
            wThreads[i] = new Thread(writeThread);
            wThreads[i].start();
        }

        for (int i = 0; i < 18; i++) {
            rThreads[i].join();
        }

        for (int i = 0; i < 0; i++) {
            wThreads[i].join();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("耗时=" + (endTime - startTime) + " ms");
    }
}
