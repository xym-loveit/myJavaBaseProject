package com.xym.myJava.concurrent;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-03 9:43
 */
public class AtomicIntegerDemo {

    public static AtomicInteger counter = new AtomicInteger(0);

    static class Visitor implements Runnable {
        @Override
        public void run() {

            for (int i = 0; i < 1000; i++) {
                counter.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ConcurrentSkipListMap map;
        ConcurrentSkipListSet set;

        int num = 1000;
        Thread[] threads = new Thread[num];
        for (int i = 0; i < num; i++) {
            threads[i] = new Thread(new Visitor());
            threads[i].start();
        }

        for (int i = 0; i < num; i++) {
            threads[i].join();
        }

        System.out.println(counter.get());
    }
}
