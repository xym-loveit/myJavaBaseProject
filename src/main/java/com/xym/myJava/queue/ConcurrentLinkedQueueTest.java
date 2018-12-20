package com.xym.myJava.queue;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-12-17 10:52
 */
public class ConcurrentLinkedQueueTest {
    public static int threadCount = 100000;
    public static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();

    static class Offer implements Runnable {
        public void run() {
            if (queue.size() == 0) {
                String ele = new Random().nextInt(Integer.MAX_VALUE) + "";
                queue.offer(ele);
                System.out.println("入队元素为" + ele);
            }
        }
    }

    static class Poll implements Runnable {
        public void run() {
            if (!queue.isEmpty()) {
                String ele = queue.poll();
                System.out.println("出队元素为" + ele);
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int x = 0; x < threadCount; x++) {
            executorService.submit(new Offer());
            executorService.submit(new Poll());
        }
        executorService.shutdown();
    }
}
