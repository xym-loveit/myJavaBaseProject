package com.xym.myJava.bloomFilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.ArrayList;
import java.util.List;

/**
 * 布隆过滤器
 *
 * @author xym
 * @create 2018-09-04 10:22
 */
public class TestMain02 {

    public static final int SIZE = 1000000;

    public static BloomFilter bloomFilter = BloomFilter.create(Funnels.integerFunnel(), SIZE, 0.0001);
    //默认不加误判参数的话是。0.03D
    //public static BloomFilter bloomFilter = BloomFilter.create(Funnels.integerFunnel(), SIZE);

    public static void main(String[] args) {
        for (int i = 0; i < SIZE; i++) {
            bloomFilter.put(i);
        }

        List<Integer> list = new ArrayList<Integer>(500);

        //故意取10000个不在过滤器里的值，看看有多少个会被认为在过滤器里
        for (int i = (SIZE + 10000); i < (SIZE + 20000); i++) {
            if (bloomFilter.mightContain(i)) {
                list.add(i);
            }
        }
        System.out.println("误判的数量：" + list.size());
    }
}
