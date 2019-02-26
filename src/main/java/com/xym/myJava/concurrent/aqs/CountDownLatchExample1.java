package com.xym.myJava.concurrent.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 其他N个线程必须引用闭锁对象，因为他们需要通知CountDownLatch对象，
 * 他们已经完成了各自的任务。这种通知机制是通过 CountDownLatch.countDown()方法来完成的；
 * 每调用一次这个方法，在构造函数中初始化的count值就减1。
 * 所以当N个线程都调 用了这个方法，count的值等于0，然后主线程就能通过await()方法，恢复执行自己的任务
 *
 * @author xym
 * @create 2019-02-26 11:29
 */
public class CountDownLatchExample1 {
    // 请求的数量
    private static final int threadCount = 550;

    public static void main(String[] args) throws InterruptedException {
        // 创建一个具有固定线程数量的线程池对象（如果这里线程池的线程数量给太少的话你会发现执行的很慢）
        ExecutorService threadPool = Executors.newFixedThreadPool(300);
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            int threadNum = i;
            threadPool.execute(() -> {
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 表示一个请求已经被完成,阻塞减一
                    countDownLatch.countDown();
                }
            });
        }
        //count到0才会执行后面内容
        countDownLatch.await();
        threadPool.shutdown();
        System.out.println("finish");
    }

    public static void test(int threadnum) throws InterruptedException {
        Thread.sleep(1000);// 模拟请求的耗时操作
        System.out.println("threadnum:" + threadnum);
        Thread.sleep(1000);// 模拟请求的耗时操作
    }
}
