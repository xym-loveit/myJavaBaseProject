package com.xym.myJava.schedule;

import java.util.concurrent.CountDownLatch;

/**
 * 模拟多线程高并发情况，发现多个线程同时消费了同一个资源的情况
 *
 * @author xym
 * @create 2018-09-04 14:58
 */
public class ThreadTest {

    /**
     * 模拟多线程高并发情况
     */
    private static final int threadNum = 10;
    private static CountDownLatch cdl = new CountDownLatch(threadNum);

    static class DelayMessage implements Runnable {
        @Override
        public void run() {
            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            JedisTest appTest = new JedisTest();
            appTest.consumerDelayMessage();
        }
    }

    public static void main(String[] args) {
        JedisTest appTest = new JedisTest();
        appTest.productionDelayMessage();
        for (int i = 0; i < threadNum; i++) {
            new Thread(new DelayMessage()).start();
            cdl.countDown();
        }
    }
}
