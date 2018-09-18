package com.xym.myJava.lambda;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * reducez求最大最小值，多值聚合为单值的情况
 *
 * @author xym
 * @create 2018-09-16 21:55
 */
public class ReduceDemo {
    public static void main(String[] args) {

        Stream<Integer> integerStream = Stream.of(12, 3, 45, 67, 8, 90);
        integerStream.reduce(Integer::max).ifPresent(System.out::println);
        integerStream = Stream.of(12, 3, 45, 67, 8, 90);
        integerStream.reduce(Integer::min).ifPresent(System.out::println);
        integerStream = Stream.of(12, 3, 45, 67, 8, 90);
        Integer maxValue1 = integerStream.reduce(0, (i, j) -> i > j ? i : j);
        System.out.println(maxValue1);
        Optional.of(maxValue1).ifPresent(System.out::println);
        integerStream = Stream.of(12, 3, 45, 67, 8, 90);
        Integer minValue = integerStream.reduce((i, j) -> i > j ? j : i).get();
        System.out.println(minValue);
        Optional.of(minValue).ifPresent(System.out::println);
    }
}
