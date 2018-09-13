package com.xym.myJava.lambda;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 并行操作
 *
 * @author xym
 * @create 2018-09-13 17:17
 */
public class LambdaDemo5 {
    public static void main(String[] args) {
        /*并行操作*/
        int count = 1000000;
        ArrayList<Object> objects = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            objects.add(UUID.randomUUID());
        }

        //100万uuid测试排序耗时

        long startTime = System.nanoTime();
        long count1 = objects.stream().sorted().count();
        System.out.println(count1);
        long endTime = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println(String.format("顺序排序消耗 %d ms", millis));

        long startTime2 = System.nanoTime();
        long count2 = objects.parallelStream().sorted().count();
        System.out.println(count2);
        long endTime2 = System.nanoTime();
        long millis2 = TimeUnit.NANOSECONDS.toMillis(endTime2 - startTime2);
        System.out.println(String.format("并行排序消耗 %d ms", millis2));
    }
}
