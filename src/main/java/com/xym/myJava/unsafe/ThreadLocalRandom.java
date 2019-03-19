package com.xym.myJava.unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-03-14 17:53
 */
public class ThreadLocalRandom {
    public static void main(String[] args) {
        java.util.concurrent.ThreadLocalRandom random = java.util.concurrent.ThreadLocalRandom.current();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(5));
        }

        try {
            Field gamma = java.util.concurrent.ThreadLocalRandom.class.getDeclaredField("GAMMA");
            gamma.setAccessible(true);
            System.out.println(gamma.get(null));
            LongAccumulator longAccumulator = new LongAccumulator((x, y) -> x + y, 5);
            LongAdder longAdder = new LongAdder();
            longAdder.add(6);
            longAccumulator.accumulate(10);
            System.out.println(longAccumulator.get());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
