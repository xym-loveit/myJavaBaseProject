package com.xym.myJava;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过可重入锁实现wait 和notify 相同的功能
 *
 * @author xym
 */
public class ReenterLockCondition implements Runnable {

    public ReentrantLock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        ReenterLockCondition reenterLockCondition = new ReenterLockCondition();
        Thread thread = new Thread(reenterLockCondition);
        thread.start();

        Thread.sleep(2000);

        /**
         * 为signal准备锁环境
         */
        reenterLockCondition.lock.lock();
        /**
         * 只有获取锁后才能调用
         */
        reenterLockCondition.condition.signal();
        reenterLockCondition.lock.unlock();

    }

    @Override
    public void run() {
        try {
            lock.lock();
            try {
                /**
                 *  等待并释放锁
                 */
                condition.await();
                System.out.println("Thread is going on");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }

    }
}
