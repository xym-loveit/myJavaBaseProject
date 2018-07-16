package com.xym.myJava;

public class Consumer extends Thread {
    private MyBlockingQueue<String> blockingQueue;

    public Consumer(MyBlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String take = blockingQueue.take();
                System.out.println("handle task " + take);
                Thread.sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
