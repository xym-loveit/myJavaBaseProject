package com.xym.myJava.lambda;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-13 17:55
 */
public class LambdaDemo7 {
    public static void main(String[] args) {
        Stream.of("a1", "a2", "a3").findFirst().ifPresent(System.out::println);
        //range循环
        IntStream.range(1, 4).forEach(System.out::println);
        //average求平均
        Arrays.stream(new int[]{1, 2, 3}).map(n -> n * 2 + 1).average().ifPresent(System.out::println);
        //
        Stream.of("a1", "a2", "a3").map(s -> s.substring(1)).mapToInt(Integer::valueOf).max().ifPresent(System.out::println);
        //基本数据流转为对象数据流
        IntStream.range(1, 4).mapToObj(s -> "a" + s).forEach(System.out::println);
        //double->int->String
        Stream.of(1.0, 2.0, 3.0).mapToInt(Double::intValue).mapToObj(i -> "a" + i).forEach(System.out::println);
    }
}
