package com.xym.myJava.thread.cnblogs_skywang12345.chapter1;

class MyThread5 extends Thread {
    public MyThread5(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println(Thread.currentThread().getName()
                    + "(" + Thread.currentThread().getPriority() + ")"
                    + ", loop " + i);
        }
    }
};

public class Demo {
    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName()
                + "(" + Thread.currentThread().getPriority() + ")");

        Thread t1 = new MyThread5("t1");    // 新建t1
        Thread t2 = new MyThread5("t2");    // 新建t2
        t1.setPriority(5);                // 设置t1的优先级为1
        t2.setPriority(10);                // 设置t2的优先级为10
        t1.start();                        // 启动t1
        t2.start();                        // 启动t2
    }
}