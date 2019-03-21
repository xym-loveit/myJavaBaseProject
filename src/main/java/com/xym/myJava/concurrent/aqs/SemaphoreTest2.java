package com.xym.myJava.concurrent.aqs;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 模拟CyclicBarrier功能
 *
 * @author xym
 * @create 2019-03-21 11:07
 */
public class SemaphoreTest2 {
    static Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) {
        //ExecutorService executorService = Executors.newFixedThreadPool(2);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 0, TimeUnit.NANOSECONDS, new ArrayBlockingQueue<>(10));
        threadPoolExecutor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " Task A over!");
            semaphore.release();
        });
        threadPoolExecutor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " Task A over!");
            semaphore.release();
        });

        try {
            semaphore.acquire(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadPoolExecutor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " Task B over!");
            semaphore.release();
        });
        threadPoolExecutor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " Task B over!");
            semaphore.release();
        });

        try {
            semaphore.acquire(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("all task over!");
        threadPoolExecutor.shutdown();
    }
}
