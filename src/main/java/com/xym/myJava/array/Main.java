package com.xym.myJava.array;

import java.util.Arrays;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-12-12 13:41
 */
public class Main {
    public static void main(String[] args) {
        Integer[] as = {1, 2, 3, 4, 5, 6, 7, 8};
        Integer[] integers = Arrays.copyOf(as, 10, Integer[].class);
        for (Integer integer : integers) {
            System.out.println(integer);
        }

    }
}
