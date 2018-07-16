package com.xym.myJava.concurrent_art;

/**
 * 线程的创建和上下文切换开销导致并发速度比串行慢
 *
 * @author xym
 */
public class ConcurrencyTest {
    private static long COUNT = 100000000L;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < COUNT; i++) {
                    a += 5;
                }
            }
        });
        thread.start();

        int b = 0;
        for (long i = 0; i < COUNT; i++) {
            b--;
        }
        thread.join();
        long time = (System.currentTimeMillis() - start);
        System.out.println("concurrency:" + time + " ms,b=" + b);
    }

    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < COUNT; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < COUNT; i++) {
            b--;
        }
        long time = (System.currentTimeMillis() - start);
        System.out.println("serial:" + time + " ms,b=" + b + ",a=" + a);
    }

}
