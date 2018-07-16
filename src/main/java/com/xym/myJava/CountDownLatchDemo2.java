package com.xym.myJava;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch第一种场景是模拟主线程需要等待各个子线程处理结果
 * <p>
 * <p>
 * 还有一种就是各个子线程等待主线程执行结果（例如 裁判向运动员发号施令的模型）
 *
 * @author xym
 */
public class CountDownLatchDemo2 implements Runnable {

    static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);

    private String name;

    public CountDownLatchDemo2(String name) {
        this.name = name;
    }

    @Override

    public void run() {
        try {
            Thread.sleep(new Random().nextInt(1) * 1000);
            System.out.println("等待裁判发号施令");
            COUNT_DOWN_LATCH.await();
            System.out.println("选手 " + name + " 开始比赛");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.submit(new CountDownLatchDemo2("name-" + i));
        }


        Thread.sleep(2000);
        System.out.println("裁判开始发号施令...");
        /**
         * 等待检查线程
         */
        COUNT_DOWN_LATCH.countDown();

        executorService.shutdown();
    }
}
