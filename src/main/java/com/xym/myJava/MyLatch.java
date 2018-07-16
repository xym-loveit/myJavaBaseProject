package com.xym.myJava;

/**
 * 实现大任务拆分成子任务线程方式CountDownLatch经典场景
 */
public class MyLatch {

    private int count;

    public MyLatch(int count) {
        this.count = count;
    }

    public synchronized void await() throws InterruptedException {
        while (count > 0) {
            wait();
        }
    }

    public synchronized void countDown() {
        count--;
        if (count <= 0) {
            notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyLatch myLatch = new MyLatch(100);
        Worker[] workers = new Worker[100];
        for (int i = 0; i < 100; i++) {
            workers[i] = new Worker(myLatch);
            workers[i].start();
        }

        myLatch.await();
        System.out.println("collect worker results");
    }

    static class Worker extends Thread {
        MyLatch myLatch;

        public Worker(MyLatch myLatch) {
            this.myLatch = myLatch;
        }

        @Override
        public void run() {
            try {
                System.out.println("当前线程--" + Thread.currentThread().getName() + "正在计算...");
                Thread.sleep((int) Math.random() * 1000);
                this.myLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
