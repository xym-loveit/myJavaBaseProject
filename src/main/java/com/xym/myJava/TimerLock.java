package com.xym.myJava;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 指定一定时间内获取锁，无需通过外部唤醒,有效避免死锁
 *
 * @author xym
 */
public class TimerLock implements Runnable {
    public static void main(String[] args) {
        TimerLock timerLock1 = new TimerLock();
        TimerLock timerLock2 = new TimerLock();

        Thread thread1 = new Thread(timerLock1, "t1");
        Thread thread2 = new Thread(timerLock2, "t2");

        thread1.start();
        thread2.start();
    }

    public static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                System.out.println("获取所成功啊...准备执行正常业务！");
                /**
                 * 这里休眠6秒导致其他线程未获取到锁
                 */
                Thread.sleep(6000);
            } else {
                System.out.println("get lock failed");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "--------------------当前线程进入finally，是否获取到了锁？" + lock.isHeldByCurrentThread());
            if (lock.isHeldByCurrentThread()) {
                System.out.println("--------------------当前线程获取到了锁，正准备释放--" + Thread.currentThread().getName());
                lock.unlock();
            }
        }
    }
}
