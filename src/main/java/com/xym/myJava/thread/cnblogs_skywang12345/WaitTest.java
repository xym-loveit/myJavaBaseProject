package com.xym.myJava.thread.cnblogs_skywang12345;

// WaitTest.java的源码
class ThreadA extends Thread {

    final Object object = new Object();

    public ThreadA(String name) {
        super(name);
    }

    @Override
    public void run() {
        synchronized (object) {
            System.out.println(object);
            System.out.println(Thread.currentThread().getName() + " call wait()");
            // 唤醒当前的wait线程
            try {
                //Thread.sleep(1000);
                object.wait();
                System.out.println("notify---------------happy");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //object.notify();sdsdsdsd
            //sdsdssdsd

        }
    }
}

public class WaitTest {

    public static void main(String[] args) throws InterruptedException {

        ThreadA t1 = new ThreadA("t1");
        t1.start();
        Thread.sleep(100);
        // 启动“线程t1”
        synchronized (t1.object) {
            System.out.println(Thread.currentThread().getName() + " notify t1");
            //t1.object.notify();
        }
        System.out.println("over");
    }
}