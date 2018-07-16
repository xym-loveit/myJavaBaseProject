package com.xym.myJava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xym
 */
public class ThreadPoolDemo {
    public static class MyTak implements Runnable {

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

    public static void main(String[] args) {
        MyTak myTak = new MyTak();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            executorService.submit(myTak);
        }

        executorService.shutdown();
    }
}
