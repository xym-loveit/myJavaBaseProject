package com.xym.myJava;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 通过自定义ThreadFactory，可以制定生产线程的逻辑
 *
 * @author xym
 */
public class ThreadFactoryDemo {

    public static class MyTak implements Runnable {

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ":Thread ID:" + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        MyTak myTak = new MyTak();

        /**
         * 使用自定义线程工厂实现newThread方法，用来创建线程
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                System.out.println("create " + thread);
                return thread;
            }
        });

        for (int i = 0; i < 5; i++) {
            threadPoolExecutor.submit(myTak);
        }

        Thread.sleep(2000);
    }
}
