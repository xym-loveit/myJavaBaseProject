package com.xym.myJava.concurrent;

import java.util.concurrent.*;

/**
 * 在使用 FutureTask最好采用带超时的get方法
 *
 * @author xym
 * @create 2019-03-21 17:38
 */
public class FutureTaskDemo {
    /**
     * 注意当前线程池个数为1
     */
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.NANOSECONDS, new ArrayBlockingQueue<>(1), new ThreadPoolExecutor.DiscardPolicy());
    static ThreadPoolExecutor executor2 = new ThreadPoolExecutor(1, 1, 0, TimeUnit.NANOSECONDS, new ArrayBlockingQueue<>(1), new MyRejectedExecutionHandler());

    public static void main(String[] args) throws Exception {
        Future<?> one = executor2.submit(() -> {
            System.out.println("start runnable one");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Future<?> two = executor2.submit(() -> {
            System.out.println("start runnable two");
        });

        Future<?> three = executor2.submit(() -> {
            try {
                System.out.println("start runnable three");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //等待任务1完毕
        System.out.println("task one =" + one.get());
        //等待任务2完毕
        System.out.println("task two =" + two.get());
        //等待任务3完毕(丢弃任务后永远阻塞------------------------------------------------)
        System.out.println("task three=" + (null == three ? null : three.get(5000, TimeUnit.MILLISECONDS)));

        executor.shutdown();
    }

    static class MyRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            if (!executor.isShutdown()) {
                if (r != null && r instanceof FutureTask) {
                    ((FutureTask) r).cancel(true);
                }
            }
        }
    }
}
