package com.xym.myJava.thread.joins;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-02-25 10:17
 */
public class ThreadB extends Thread {

    @Override
    public void run() {
        System.out.println("B thread is run.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("B thread is over.");
    }

    public synchronized void myService() {
        for (int i = 0; i < 100; i++) {
            System.out.println("myService do sth." + i);
        }
    }
}
