package com.xym.myJava;

import java.util.Random;
import java.util.stream.IntStream;

public class RandomDemo {
    public static void main(String[] args) {
        Random random = new Random();
        IntStream ints = random.ints();
        /**
         * 创建一个范围随机数[0-100]包含0但不包含100
         */
        IntStream ints2 = random.ints(0, 100);
        ints.limit(10).forEach(x -> System.out.println(x));
        ints2.limit(10).forEach(System.out::println);


    }
}
