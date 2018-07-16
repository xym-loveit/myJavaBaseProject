package com.xym.myJava.lock;

import java.util.concurrent.TimeUnit;

/**
 * synchronized同步的缺陷：
 * <p>
 * 1、无法控制阻塞时长（t2如果想在2分钟后获取执行权没办法控制）
 * <p>
 * 2、阻塞不可被中断（对t2发生中断操作后，t2无反应,还是blocked状态）
 *
 * @author xym
 * @create 2018-07-16 17:42
 */
public class SynchronizedDefect {

    public static void main(String[] args) {
        SynchronizedDefect synchronizedDefect = new SynchronizedDefect();
        new Thread("t1") {
            @Override
            public void run() {
                synchronizedDefect.syncMethod();
            }
        }.start();

        try {
            //确保t1正常启动
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                synchronizedDefect.syncMethod();
            }
        };

        t2.start();

        //确保t2正常启动
        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //试图中断t2
        t2.interrupt();

        System.out.println(t2.isInterrupted());
        for (int i = 0; i < 100; i++) {
            t2.interrupt();
            System.out.println(t2.isInterrupted());
            System.out.println(t2.getState());
        }
    }


    /**
     * 同步方法
     */
    public synchronized void syncMethod() {
        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
