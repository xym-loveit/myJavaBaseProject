package com.xym.myJava.thread.cnblogs_skywang12345.chapter1;

class MyThread2 extends Thread {

    public MyThread2(String name) {
        super(name);
    }

    @Override
    public void run() {
        synchronized(this) {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100); // 休眠100ms
                    System.out.println(Thread.currentThread().getName() + " loop " + i);
                }
            } catch (InterruptedException ie) {
            }
        }
    }
}

public class Demo1_1 {

    public static void main(String[] args) {
        Thread t1 = new MyThread2("t1");  // 新建“线程t1”
        Thread t2 = new MyThread2("t2");  // 新建“线程t2”
        t1.start();                          // 启动“线程t1”
        t2.start();                          // 启动“线程t2” 
    }
}