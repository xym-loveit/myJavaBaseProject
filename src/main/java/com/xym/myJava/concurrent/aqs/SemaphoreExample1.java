package com.xym.myJava.concurrent.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore管理一系列许可证。每个acquire方法阻塞，
 * 直到有一个许可证可以获得然后拿走一个许可证；
 * 每个release方法增加一个许可证，这可能会释放一个阻塞的acquire方法。
 * 然而，其实并没有实际的许可证这个对象，Semaphore只是维持了一个可获得许可证的数量
 *
 * @author xym
 * @create 2019-02-26 11:09
 */
public class SemaphoreExample1 {
    // 请求的数量
    private static final int threadCount = 550;

    public static void main(String[] args) {
        // 创建一个具有固定线程数量的线程池对象（如果这里线程池的线程数量给太少的话你会发现执行的很慢）
        ExecutorService threadPool = Executors.newFixedThreadPool(300);
        // 一次只能允许执行的线程数量。
        final Semaphore semaphore = new Semaphore(20);
        for (int i = 0; i < threadCount; i++) {
            final int threadnum = i;
            //Lambda 表达式的运用
            threadPool.execute(() -> {
                try {
                    //获取一个许可，所以可运行线程数量为20/1=20
                    semaphore.acquire();
                    test(threadnum);
                    // 释放一个许可
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
        System.out.println("finish");
    }

    public static void test(int threadnum) throws InterruptedException {
        Thread.sleep(1000);// 模拟请求的耗时操作
        System.out.println("threadnum:" + threadnum);
        Thread.sleep(1000);// 模拟请求的耗时操作
    }
}
