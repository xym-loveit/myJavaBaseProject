package com.xym.myJava.thread.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-02-25 16:34
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            //创建WorkerThread对象（WorkerThread类实现了Runnable 接口）
            Runnable worker = new WorkerThread("" + i);
            //执行Runnable
            executor.execute(worker);
        }

        //终止线程池
        executor.shutdown();
        while (!executor.isTerminated()) {
            //System.out.println("未终止...");
        }
        System.out.println("Finished all threads " + executor.isShutdown());
    }
}
