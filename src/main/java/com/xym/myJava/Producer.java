package com.xym.myJava;

/**
 * @author xym
 */
public class Producer extends Thread {

    private MyBlockingQueue<String> blockingQueue;

    public Producer(MyBlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        int num = 0;
        while (true) {
            String take = String.valueOf(num);
            try {
                blockingQueue.put(take);
                System.out.println("produce take " + take);
                num++;
                Thread.sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
