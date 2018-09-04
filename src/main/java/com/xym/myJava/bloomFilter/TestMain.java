package com.xym.myJava.bloomFilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * 布隆过滤器
 *
 * @author xym
 * @create 2018-09-04 10:22
 */
public class TestMain {

    public static final int SIZE = 1000000;

    public static BloomFilter bloomFilter = BloomFilter.create(Funnels.integerFunnel(), SIZE);

    public static void main(String[] args) {
        for (int i = 0; i < SIZE; i++) {
            bloomFilter.put(i);
        }

        long startTime = System.nanoTime();
        //判断这一百万个数中是否包含29999这个数
        if (bloomFilter.mightContain(29999)) {
            System.out.println("命中了");
        }
        long endTime = System.nanoTime();
        System.out.println("程序耗时： " + (endTime - startTime) / 1000000.0D + "毫秒");
    }
}
