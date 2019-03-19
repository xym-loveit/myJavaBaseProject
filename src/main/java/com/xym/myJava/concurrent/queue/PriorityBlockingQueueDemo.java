package com.xym.myJava.concurrent.queue;

import io.netty.util.internal.ThreadLocalRandom;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 优先级阻塞队列,自动增长无界
 *
 * @author xym
 * @create 2019-03-18 23:16
 */
public class PriorityBlockingQueueDemo {
    public static final AtomicInteger ATOMIC_COUNT = new AtomicInteger(0);

    public static void main(String[] args) {
        PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue<>(10);
        ThreadLocalRandom random = ThreadLocalRandom.current();
        ExecutorService executors = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            final int ii = i;
            ((ExecutorService) executors).submit(() -> {
                for (int j = 0; j < 20; j++) {
                    Task task = new Task();
                    task.setName("task " + ii + "--" + j);
                    task.setPriority(random.nextInt(100));
                    priorityBlockingQueue.offer(task);
                    ATOMIC_COUNT.incrementAndGet();
                }
            });
        }

        executors.shutdown();
        while (!(executors).isTerminated()) {
            Thread.yield();
            System.out.println("等待中。。。。");
        }
        System.out.println("线程池已终止..." + ((ThreadPoolExecutor) executors).getTaskCount() + "--" + ATOMIC_COUNT);
        while (!priorityBlockingQueue.isEmpty()) {
            System.out.println(priorityBlockingQueue.poll());
        }

    }

    static class Task implements Comparable<Task> {
        //任务优先级
        private int priority;
        //名称
        private String name;

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Task{" +
                    "priority=" + priority +
                    ", name='" + name + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Task task) {
            return this.getPriority() - task.getPriority();
        }
    }
}
