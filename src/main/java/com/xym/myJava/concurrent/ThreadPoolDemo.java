package com.xym.myJava.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-03-21 17:18
 */
public class ThreadPoolDemo {
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 0, TimeUnit.NANOSECONDS, new ArrayBlockingQueue<>(10));

    public static void main(String[] args) {
        for (int s = 0; s < 5; s++) {
            test();
        }

        //executor.shutdown();
        while (!executor.isTerminated()) {
            System.out.println(executor.toString());
            Thread.yield();
        }
        System.out.println("第二波开始");
        for (int s = 0; s < 5; s++) {
            test();
        }
        executor.shutdown();
    }


    public static void test() {
        for (int i = 0; i < 2; i++) {
            final int ii = i;
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "===" + ii);
            });
        }
    }

}
