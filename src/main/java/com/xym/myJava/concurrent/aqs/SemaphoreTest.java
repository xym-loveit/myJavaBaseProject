package com.xym.myJava.concurrent.aqs;

import java.util.concurrent.Semaphore;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-03-21 11:00
 */
public class SemaphoreTest {
    public static final Semaphore SEMAPHORE = new Semaphore(0);

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "子线程先执行！");
            //许可增加，默认每次增加1
            SEMAPHORE.release();
        }, "ThreadA").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "子线程先执行！");
            //许可增加,默认每次增加1
            SEMAPHORE.release();
        }, "ThreadB").start();

        try {
            //开始许可数为0，自旋阻塞
            SEMAPHORE.acquire(2);
            System.out.println("主线程开始执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
