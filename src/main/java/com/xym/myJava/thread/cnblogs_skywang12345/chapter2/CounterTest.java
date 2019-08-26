package com.xym.myJava.thread.cnblogs_skywang12345.chapter2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-24 19:04
 */
public class CounterTest {

    public static void main(String[] args) throws Exception {
        int NUM_OF_THREADS = 1000;
        int NUM_OF_INCREMENTS = 100;
        ExecutorService service = Executors.newFixedThreadPool(NUM_OF_THREADS);
        Counter counter = new CASCounter();
        long before = System.currentTimeMillis();
        for (int i = 0; i < NUM_OF_THREADS; i++) {
            service.submit(new CounterClient(counter, NUM_OF_INCREMENTS));
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.MINUTES);
        long after = System.currentTimeMillis();
        System.out.println("Counter result: " + counter.getCounter());
        System.out.println("Time passed in ms:" + (after - before));
    }

}
