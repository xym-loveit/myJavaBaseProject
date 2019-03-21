package com.xym.myJava.concurrent.aqs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

/**
 * 采用CyclicBarrier多线程合并结果集
 *
 * @author xym
 * @create 2019-03-21 9:52
 */
public class CyclicBarrierExample5 {

    public static final Map<Thread, Long> SUM_MAP = new HashMap<>(4);

    public static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(4, 4, 0, TimeUnit.NANOSECONDS, new ArrayBlockingQueue<>(10)
    );

    public static long sum = 0;

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(4, () -> {
        Set<Map.Entry<Thread, Long>> entries = SUM_MAP.entrySet();
        Iterator<Map.Entry<Thread, Long>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Thread, Long> next = iterator.next();
            sum += next.getValue();
            System.out.println("sum=" + sum);
        }
    });

    private static final int NUMBER = 1000000;


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        sum = 0;
        for (int i = 0; i <= NUMBER; i++) {
            sum += i;
        }
        System.out.println("sum=" + sum);
        System.out.println("cost " + (System.currentTimeMillis() - start) + " ms");
        sum = 0;

        start = System.currentTimeMillis();
        EXECUTOR.execute(() -> {
            System.out.println("Thread id=" + Thread.currentThread().getId());
            long tempSum = sum(1, 250000);
            SUM_MAP.put(Thread.currentThread(), tempSum);
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        EXECUTOR.execute(() -> {
            System.out.println("Thread id=" + Thread.currentThread().getId());
            long tempSum = sum(250001, 500000);
            SUM_MAP.put(Thread.currentThread(), tempSum);
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        EXECUTOR.execute(() -> {
            System.out.println("Thread id=" + Thread.currentThread().getId());
            long tempSum = sum(500001, 750000);
            SUM_MAP.put(Thread.currentThread(), tempSum);
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        EXECUTOR.execute(() -> {
            System.out.println("Thread id=" + Thread.currentThread().getId());
            long tempSum = sum(750001, 1000000);
            SUM_MAP.put(Thread.currentThread(), tempSum);
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        EXECUTOR.shutdown();
        while (!EXECUTOR.isTerminated()) {
            Thread.yield();
        }

        long end = System.currentTimeMillis();
        System.out.println("pool cost " + (end - start) + " ms");
    }

    private static long sum(int start, int end) {
        if (end == start) {
            return end;
        }
        long sum = 0;
        for (int i = start; i <= end; i++) {
            sum += i;
        }
        return sum;
    }
}
