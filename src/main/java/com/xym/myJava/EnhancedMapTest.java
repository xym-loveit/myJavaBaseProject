package com.xym.myJava;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xym
 */
public class EnhancedMapTest {
    public static void main(String[] args) throws InterruptedException {

        Map<String, Integer> orgMap = new HashMap<>();
        orgMap.put("a", 1);
        orgMap.put("b", 2);
        orgMap.put("c", 3);
        orgMap.put("d", 4);


        /**
         * 初始化一些值
         */
        EnhancedMap<String, Integer> enhancedMap = new EnhancedMap<>(orgMap);

        /**
         * 创建100个线程尝试去改变安全类型map中的值
         */
        MyThread[] thread = new MyThread[1000];
        for (int i = 0; i < 1000; i++) {
            thread[i] = new MyThread("i", enhancedMap);
            thread[i].start();
        }


        for (int i = 0; i < 1000; i++) {
            thread[i].join();
        }

        System.out.println(enhancedMap);
    }

    static class MyThread extends Thread {

        private EnhancedMap<String, Integer> map;

        public MyThread(String name, EnhancedMap<String, Integer> map) {
            super(name);
            this.map = map;
        }

        @Override
        public void run() {
            /**
             * 不停地尝试修改原值
             */
            for (int i = 0; i < 1000; i++) {
                map.putIfAbsent("a", i);
                try {
                    Thread.sleep(BigDecimal.valueOf(10 * Math.random()).longValue());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
