package com.xym.myJava;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch第一种场景是模拟主线程需要等待各个子线程处理结果
 * <p>
 * 还有一种就是各个子线程等待主线程执行结果（例如 裁判向运动员发号施令的模型）
 *
 * @author xym
 */
public class CountDownLatchDemo implements Runnable {

    static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(10);
    static final CountDownLatchDemo DEMO = new CountDownLatchDemo();


    @Override

    public void run() {
        try {
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println("check complete!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            COUNT_DOWN_LATCH.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.submit(DEMO);
        }

        /**
         * 等待检查线程
         */
        COUNT_DOWN_LATCH.await();
        System.out.println("Fire!");

        executorService.shutdown();
    }
}
