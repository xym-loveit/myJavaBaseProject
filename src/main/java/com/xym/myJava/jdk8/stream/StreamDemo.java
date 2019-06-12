package com.xym.myJava.jdk8.stream;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-06-11 11:40
 */
public class StreamDemo {
    public static void main(String[] args) {
        Stream<Double> generate = Stream.generate(() -> Math.PI);
        generate.close();
        //Stream<BigDecimal> iterate = Stream.iterate(BigDecimal.ZERO, (n) -> n.add(BigDecimal.ONE));
        //iterate.forEach(System.out::println);
        try {
            Files.lines(Paths.get("d:/gather.txt")).parallel().forEach(System.out::println);
            //限制多少条
            Stream.generate(Math::random).limit(100).forEach(System.out::println);
            //跳过多少个
            Stream.of("a b c d e f".split(" ")).skip(1).forEach(System.out::println);
            //连接流
            Stream<? extends Serializable> concat = Stream.concat(Stream.of("a", "b", "c", "d"), Stream.of(1, 2, 3, 4));

            System.out.println(concat.count());
            //peek
            Object[] objects = Stream.iterate(1, (n) -> n * 2).peek(e -> System.out.println("fetching " + e)).limit(20).toArray();
            System.out.println(Arrays.toString(objects));
            //查找第一个
            Stream.of(1, 2, 3, 4, 5, 6).filter((n) -> n % 2 == 0).findFirst().ifPresent(System.out::println);
            //聚合函数
            System.out.println(IntStream.range(1, 101).sum());
            Stream.iterate(1, (n) -> n + 1).limit(100).reduce((n1, n2) -> n1 + n2).ifPresent(System.out::println);
            Stream.iterate(1, (n) -> n + 1).limit(100).reduce(Integer::sum).ifPresent(System.out::println);
            Integer reduce = Stream.iterate(1, (n) -> n + 1).limit(100).reduce(50, (n1, n2) -> n1 + n2);
            System.out.println(reduce);
            //求集合里面所有元素的长度
            System.out.println(Stream.of("aaa", "b", "c2", "d").mapToInt(String::length).sum());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
