package com.xym.myJava;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 该实例使用显示锁，用来说明：一个线程启动后，在执行一项操作前，等待主线程给它指令，收到指令后才执行
 *
 * @author xym
 */
public class WaitThreadByLock extends Thread {

    private volatile boolean fire = false;
    //显示锁
    private Lock lock = new ReentrantLock();
    //现实条件
    private Condition condition = lock.newCondition();

    @Override
    public void run() {
        try {
            lock.lock();
            while (!fire) {
                System.out.println("等待中...");
                condition.await();
            }
            System.out.println("唤醒后执行...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void fire() {
        lock.lock();
        try {
            this.fire = true;
            //唤醒显示条件condition的await
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WaitThreadByLock waitThreadByLock = new WaitThreadByLock();
        waitThreadByLock.start();
        Thread.sleep(1000);
        System.out.println("fire");
        waitThreadByLock.fire();
    }
}
