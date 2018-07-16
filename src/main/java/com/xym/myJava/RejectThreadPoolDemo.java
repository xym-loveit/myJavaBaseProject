package com.xym.myJava;

import java.util.concurrent.*;

/**
 * 自定义拒绝策略的线程池，观察执行机制
 *
 * @author xym
 */
public class RejectThreadPoolDemo {

    /**
     * 模拟线程池将要执行的任务
     */
    public static class MyTask implements Runnable {
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
        MyTask myTask = new MyTask();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 8, 0, TimeUnit.MILLISECONDS, new SynchronousQueue(), Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println(r.toString() + " is discard");
            }
        });


        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            threadPoolExecutor.submit(myTask);
            Thread.sleep(100);
            System.out.println("正在执行的任务数：" + threadPoolExecutor.getActiveCount() + "---核心任务数：" + threadPoolExecutor.getCorePoolSize() + "--池最大任务数：" + threadPoolExecutor.getLargestPoolSize() + "--" + threadPoolExecutor.getMaximumPoolSize() + "--总任务数：" + threadPoolExecutor.getTaskCount() + "--完成的任务数：" + threadPoolExecutor.getCompletedTaskCount());
        }

    }
}
