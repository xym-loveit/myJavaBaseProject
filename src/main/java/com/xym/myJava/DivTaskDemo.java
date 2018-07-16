package com.xym.myJava;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xym
 */
public class DivTaskDemo implements Runnable {

    private int a;
    private int b;

    public DivTaskDemo(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        double re = a / b;
        System.out.println(re);
    }

    public static void main(String[] args) {
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<>());
        TraceThreadPoolExecutor threadPoolExecutor = new TraceThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<>());


        for (int i = 0; i < 5; i++) {
            threadPoolExecutor.submit(new DivTaskDemo(100, i));
        }
    }
}
