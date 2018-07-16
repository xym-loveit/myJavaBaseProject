package com.xym.myJava;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author xym
 */
public class StreamConvert {
    public static void main(String[] args) {
        Stream.of("a1", "a2", "a3").map(n -> n.substring(1)).mapToInt(Integer::parseInt).max().ifPresent(System.out::println);
        IntStream.range(1, 4).mapToObj(j -> "a" + j).forEach(System.out::println);
        /**
         * double-->int-->obj
         */
        Stream.of(1.0, 2.0, 3.0).mapToInt(Double::intValue).mapToObj(i -> "a" + i).forEach(System.out::println);
    }
}
