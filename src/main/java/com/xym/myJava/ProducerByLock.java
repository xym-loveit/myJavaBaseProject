package com.xym.myJava;

/**
 * @author xym
 */
public class ProducerByLock extends Thread {

    private MyBlockQueue<String> myBlockQueue;

    public ProducerByLock(MyBlockQueue<String> myBlockQueue) {
        this.myBlockQueue = myBlockQueue;
    }

    @Override
    public void run() {
        int num = 0;
        while (true) {
            String take = String.valueOf(num);
            try {
                myBlockQueue.put(take);
                System.out.println("produce take " + take);
                num++;
                Thread.sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
