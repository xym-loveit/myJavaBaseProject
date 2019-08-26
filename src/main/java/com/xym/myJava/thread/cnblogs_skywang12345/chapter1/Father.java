package com.xym.myJava.thread.cnblogs_skywang12345.chapter1;

// 主线程
public class Father extends Thread {
    public Father(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(String.format("%s--%s", Thread.currentThread().getName(), "run--start"));
        Son s = new Son("son");
        s.start();
        try {
            s.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("%s--%s", Thread.currentThread().getName(), "run--end"));
    }

    public static void main(String[] args) {
        System.out.println(String.format("%s--%s", Thread.currentThread().getName(), "run--start"));
        new Father("father").start();
        System.out.println(String.format("%s--%s", Thread.currentThread().getName(), "run---end"));
    }
}

// 子线程
class Son extends Thread {
    public Son(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(String.format("%s--%s", Thread.currentThread().getName(), "run-----running"));
    }
}