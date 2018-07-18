package com.xym.myJava.futures;

import java.util.concurrent.TimeUnit;

/**
 * 带有返回值的future测试
 *
 * @author xym
 * @create 2018-07-18 14:58
 */
public class FutureServiceWithResult {
    public static void main(String[] args) {
        FutureService<String, Integer> futureService = FutureService.newService();
        Future<Integer> future = futureService.submit(new Task<String, Integer>() {
            @Override
            public Integer get(String input) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return input.length();
            }
        }, "Hello");

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
