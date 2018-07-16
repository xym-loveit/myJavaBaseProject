package com.xym.myJava;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 扩展线程池，监控线程执行过程
 *
 * @author xym
 */
public class ExtThreadPoll {

    public static void main(String[] args) throws InterruptedException {

        int i1 = Runtime.getRuntime().availableProcessors();
        System.out.println("可用cpu="+i1);


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>()) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完成 " + ((MyTask) r).name);
            }

            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行" + ((MyTask) r).name);
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出！");
            }
        };


        for (int i = 0; i < 5; i++) {
            threadPoolExecutor.execute(new MyTask("TASK " + i));
            Thread.sleep(10);
        }

        threadPoolExecutor.shutdown();

        System.out.println("----------------------线程池的关闭是非阻塞...");
    }


    public static class MyTask implements Runnable {
        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("正在执行 Thread:ID：" + Thread.currentThread().getId() + ",Task name=" + name);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
