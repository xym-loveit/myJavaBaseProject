package com.xym.myJava.current_limiting;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-12 10:42
 */
public class SemaphoreDemo {

    private static ExecutorService exec = Executors.newCachedThreadPool();

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < 20; i++) {
            exec.execute(() -> {
                try {
                    //获得许可
                    semaphore.acquire();
                    System.out.println(Thread.currentThread());
                    //同时只有5个同求可以到达这里
                    Thread.sleep((long) (Math.random() * 100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //释放许可
                    semaphore.release();
                    System.out.println("剩余许可："+semaphore.availablePermits());
                }
            });
        }

        exec.shutdown();
    }
}
