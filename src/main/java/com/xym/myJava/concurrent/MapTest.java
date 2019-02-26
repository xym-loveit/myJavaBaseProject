package com.xym.myJava.concurrent;

import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-02-20 16:50
 */
public class MapTest {
    public static void main(String[] args) {
        Hashtable<String, String> hashtable = new Hashtable<>(100);
        Map<String, String> hashMap = new ConcurrentHashMap<>();
        //CountDownLatch latch = new CountDownLatch(199);
        //CountDownLatch latch2 = new CountDownLatch(199);
        long s = System.currentTimeMillis();
        AtomicInteger count = new AtomicInteger();
        for (int i = 0; i < 100; i++) {
            int ii = i;
            Runnable runnable = () -> {
                hashtable.put(String.valueOf(ii), String.valueOf((ii << 1)));
                //latch.countDown();
            };
            new Thread(runnable).start();

            if (!hashtable.isEmpty()) {
                count.incrementAndGet();
                System.out.println(count);
                new Thread(() -> {
                    hashtable.get(String.valueOf(new Random().nextInt(hashtable.size() - 1)));
                    //latch.countDown();
                }).start();
            }

            //}

            //try {
            //latch.await();
            long e = System.currentTimeMillis();
            System.out.println(hashtable.size() + "--hashtable const " + (e - s) + " ms");
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
        }


        //s = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            int iii = i;
            Runnable runnable2 = () -> {
                hashMap.put(String.valueOf(iii), String.valueOf((iii << 1)));
                //latch2.countDown();
            };
            new Thread(runnable2).start();

            if (!hashMap.isEmpty()) {
                new Thread(() -> {
                    hashMap.get(String.valueOf(new Random().nextInt(hashMap.size() - 1)));
                    //latch2.countDown();
                }).start();
            }

        }

        while (true) {
            Thread.yield();
        }

        //try {
            //latch2.await();
            //long e = System.currentTimeMillis();
            //System.out.println(hashMap.size() + "--hashMap const " + (e - s) + " ms");
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}

    }
}
