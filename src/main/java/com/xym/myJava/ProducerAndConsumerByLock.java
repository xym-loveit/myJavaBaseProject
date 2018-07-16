package com.xym.myJava;

/**
 * @author xym
 */
public class ProducerAndConsumerByLock {
    public static void main(String[] args) {
        MyBlockQueue<String> blockingQueue = new MyBlockQueue<>(100);
        new ProducerByLock(blockingQueue).start();
        new ConsumerByLock(blockingQueue).start();
    }
}
