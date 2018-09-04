package com.xym.myJava.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-03 15:26
 */
public class UnsafeHashMap {
    public static void main(String[] args) {
        final Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread() {
                Random random = new Random();

                @Override
                public void run() {
                    for (int i1 = 0; i1 < 1000; i1++) {
                        map.put(random.nextInt(), 1);
                    }
                }
            };

            thread.start();
        }

        System.out.println(map.size());
    }
}
