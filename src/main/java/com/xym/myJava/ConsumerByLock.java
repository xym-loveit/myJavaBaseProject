package com.xym.myJava;

public class ConsumerByLock extends Thread {
    private MyBlockQueue<String> myBlockQueue;

    public ConsumerByLock(MyBlockQueue<String> myBlockQueue) {
        this.myBlockQueue = myBlockQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String take = myBlockQueue.take();
                System.out.println("handle task " + take);
                Thread.sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
