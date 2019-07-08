package com.xym.myJava.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-07-05 17:44
 */
public class FlatMapDemo {
    public static void main(String[] args) {


        //数列处理
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<Integer> list1 = Arrays.asList(3, 4);
        List<int[]> collect = list.stream().flatMap(i -> list1.stream().map(j -> new int[]{i, j})).collect(Collectors.toList());
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        collect.forEach(ints -> {
                    List<String> collect1 = IntStream.of(ints).mapToObj(String::valueOf).collect(Collectors.toList());
                    joiner.add("(" + String.join(",", collect1) + ")");
                }
        );
        System.out.println(joiner.toString());
    }
}
