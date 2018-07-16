package com.xym.myJava;

/**
 * @author xym
 */
public class ProducerAndConsumer {
    public static void main(String[] args) {
        MyBlockingQueue<String> blockingQueue = new MyBlockingQueue<>(100);
        new Producer(blockingQueue).start();
        new Consumer(blockingQueue).start();
    }
}
