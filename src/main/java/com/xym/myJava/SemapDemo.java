package com.xym.myJava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 使用信号量演示多线程访问共享资源，可做流控
 *
 * @author xym
 */
public class SemapDemo implements Runnable {

    /**
     * 创建5个许可的信号量
     */
    final Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newFixedThreadPool(20);
        SemapDemo semapDemo = new SemapDemo();
        for (int i = 0; i < 20; i++) {
            executorService.submit(semapDemo);
        }

        executorService.shutdown();
    }

    @Override
    public void run() {
        //获取许可
        try {
            semaphore.acquire();
            //临界区开始

            //只能同时有5个线程访问该区域

            //模拟耗时操作
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId() + ":done!");


            //临界区结束
            semaphore.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
