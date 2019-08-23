package com.xym.myJava.thread.cnblogs_skywang12345;

// RunnableTest.java 源码
class MyThread1 implements Runnable {
    private int ticket = 10;

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (this.ticket > 0) {
                System.out.println(Thread.currentThread().getName() + " 卖票：ticket" + this.ticket--);
            }
        }
    }
};

public class RunnableTest {
    public static void main(String[] args) {
        MyThread1 mt = new MyThread1();

        // 启动3个线程t1,t2,t3(它们共用一个Runnable对象)，这3个线程一共卖10张票！
        Thread t1 = new Thread(mt);
        Thread t2 = new Thread(mt);
        Thread t3 = new Thread(mt);
        t1.start();
        t1.run();
        //t2.start();
        //t3.start();
    }
}