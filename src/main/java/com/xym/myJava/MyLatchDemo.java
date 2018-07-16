package com.xym.myJava;

/**
 * 使用mylatch实现同时开始
 *
 * @author xym
 */
public class MyLatchDemo {

    /**
     *
     */
    static class Racer extends Thread {
        MyLatch myLatch;

        public Racer(MyLatch myLatch) {
            this.myLatch = myLatch;
        }

        @Override
        public void run() {
            try {
                this.myLatch.await();
                System.out.println("start run " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyLatch myLatch = new MyLatch(1);
        Racer[] racers = new Racer[10];
        for (int i = 0; i < 10; i++) {
            racers[i] = new Racer(myLatch);
            racers[i].start();
        }
        Thread.sleep(1000);
        myLatch.countDown();
    }
}
