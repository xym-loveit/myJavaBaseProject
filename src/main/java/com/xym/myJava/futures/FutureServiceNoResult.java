package com.xym.myJava.futures;

import java.util.concurrent.TimeUnit;

/**
 * 无返回结果的FutureService测试
 *
 * @author xym
 * @create 2018-07-18 14:55
 */
public class FutureServiceNoResult {
    public static void main(String[] args) {
        FutureService<Object, Object> futureService = FutureService.newService();
        Future<?> future = futureService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("i am finish done");
            }
        });

        try {
            System.out.println(Thread.currentThread().getName() + " will be blocked");
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
