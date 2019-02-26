package com.xym.myJava.thread;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-02-22 11:04
 */
public class ThreadPriorityDemo {


    public static void main(String[] args) {
        System.out.println("main thread begin priority="
                + Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(6);
        System.out.println("main thread end   priority="
                + Thread.currentThread().getPriority());
        new Thread1().start();
    }
}

class Thread1 extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread1 run priority=" + this.getPriority());
        new Thread2().start();
    }
}

class Thread2 extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread2 run priority=" + this.getPriority());
    }
}