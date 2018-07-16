package com.xym.myJava;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 生产者消费者协作队列
 *
 * @author xym
 */
public class MyBlockingQueue<E> {

    private Queue<E> queue;

    private int limit;

    public MyBlockingQueue(int limit) {
        this.queue = new ArrayDeque<>(limit);
        this.limit = limit;
    }

    public synchronized void put(E e) throws InterruptedException {
        while (queue.size() == limit) {
            wait();
        }
        queue.add(e);
        notifyAll();
    }

    public synchronized E take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        E v = queue.poll();
        notifyAll();
        return v;
    }
}
