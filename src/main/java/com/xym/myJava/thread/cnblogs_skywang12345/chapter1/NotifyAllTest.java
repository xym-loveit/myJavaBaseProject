package com.xym.myJava.thread.cnblogs_skywang12345.chapter1;

public class NotifyAllTest {

    public final static Object obj = new Object();

    final void add(){

    }

    public static void main(String[] args) {

        ThreadA t1 = new ThreadA("t1");
        t1.setPriority(8);
        ThreadA t2 = new ThreadA("t2");
        t2.setPriority(7);
        ThreadA t3 = new ThreadA("t3");
        t3.setPriority(6);
        t1.start();
        t2.start();
        t3.start();

        //try {
        //    System.out.println(Thread.currentThread().getName() + " sleep(3000)");
        //    Thread.sleep(3000);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        Thread.yield();

        synchronized (obj) {
            // 主线程等待唤醒。
            System.out.println(Thread.currentThread().getName() + " notifyAll()");
            obj.notifyAll();
        }
    }

    static class ThreadA extends Thread {

        public ThreadA(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (obj) {
                try {
                    // 打印输出结果
                    System.out.println(Thread.currentThread().getName() + " wait");

                    // 当前线程wait
                    obj.wait();

                    //Thread.sleep(1000);
                    // 打印输出结果
                    System.out.println(Thread.currentThread().getName() + " continue");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}