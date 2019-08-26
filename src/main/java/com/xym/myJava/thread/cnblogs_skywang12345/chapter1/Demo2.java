package com.xym.myJava.thread.cnblogs_skywang12345.chapter1;

class Count {

    // 含有synchronized同步块的方法
    public void synMethod() throws InterruptedException {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(1000); // 休眠100ms
                System.out.println(Thread.currentThread().getName() + " synMethod loop " + i);
            }

        }
    }

    // 非同步的方法
    public void nonSynMethod() throws InterruptedException {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " nonSynMethod loop " + i);
            }
        }
    }
}

public class Demo2 {

    public static void main(String[] args) {
        final Count count = new Count();
        // 新建t1, t1会调用“count对象”的synMethod()方法
        Thread t1 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            count.synMethod();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, "t1");

        // 新建t2, t2会调用“count对象”的nonSynMethod()方法
        Thread t2 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            count.nonSynMethod();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, "t2");


        t1.start();  // 启动t1
        t2.start();  // 启动t2
    }
}