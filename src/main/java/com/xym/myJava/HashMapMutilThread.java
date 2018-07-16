package com.xym.myJava;

import java.util.HashMap;
import java.util.Map;

/**
 * 多线程环境下 使用不安全的hashmap出现莫名的线程安全问题
 *
 * @author xym
 */
public class HashMapMutilThread {

    static Map<String, String> map = new HashMap<>();

    public static class AddThread implements Runnable {
        int start = 0;

        public AddThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i < 100000; i += 2) {
                map.put(String.valueOf(i), Integer.toBinaryString(i));
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new HashMapMutilThread.AddThread(0));
        Thread t2 = new Thread(new HashMapMutilThread.AddThread(1));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(map.size());
    }
}
