package com.xym.myJava.thread.cnblogs_skywang12345;

// YieldTest.java的源码
class Runnable2 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s [%d]:%d\n", Thread.currentThread().getName(), Thread.currentThread().getPriority(), i);
            // i整除4时，调用yield
            if (i % 4 == 0) {
                //yield没有释放锁
                Thread.yield();
            }
        }
    }
}

public class YieldTest {
    public static void main(String[] args) {
        Runnable2 runnable2 = new Runnable2();
        //Runnable2 runnable1 = new Runnable2();
        Thread t1 = new Thread(runnable2, "t1");
        Thread t2 = new Thread(runnable2, "t2");
        t1.start();
        t2.setPriority(8);
        t2.start();
    }
}