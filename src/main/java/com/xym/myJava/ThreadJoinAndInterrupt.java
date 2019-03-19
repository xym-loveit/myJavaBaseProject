package com.xym.myJava;

import java.util.concurrent.TimeUnit;

/**
 * 线程的join使用
 *
 * @author xym
 * @create 2019-03-14 10:16
 */
public class ThreadJoinAndInterrupt {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println("thread1 begin run");
            for (; ; ) {

            }
        });

        Thread mainThread = Thread.currentThread();

        Thread thread2 = new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(1);
                //中断主线程阻塞
                mainThread.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //启动线程
        thread1.start();
        thread2.start();

        //等待线程1结束，当前线程（主线程阻塞）
        try {
            System.out.println("主线程join线程一,主线程阻塞");
            thread1.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " 被中断");
            e.printStackTrace();
        }
    }
}
