package com.xym.myJava.thread.executors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-02-25 15:42
 */
public class ThreadPollDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable runnable = () -> {
            System.out.println("runnable run!");
        };

        //ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 8, 30, TimeUnit.SECONDS, new ArrayBlockingQueue(100));
        //executor.execute(runnable);
        //executor.shutdown();
        //
        //ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(4);
        //poolExecutor.schedule(runnable, 5, TimeUnit.SECONDS);
        //poolExecutor.shutdown();

        //ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(4);
        //Future<?> submit = poolExecutor.submit(runnable);
        //try {
        //    System.out.println(submit.get());
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //} catch (ExecutionException e) {
        //    e.printStackTrace();
        //}
        //poolExecutor.shutdown();

        ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(4);
        Future<Integer> future = poolExecutor.submit(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 2 << 3;
                }
        );

        System.out.println(future.get());
        poolExecutor.shutdown();
    }
}
