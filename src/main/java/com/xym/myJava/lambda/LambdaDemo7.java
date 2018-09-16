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


///////////////////////////////////////////////////////////////////////调用链的执行顺序会影响到执行性能///////////////////////////////////////////////////////////////////////////
        /**
         * 衔接操作只在终止操作调用时被执行
         */
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                });

        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                }).forEach(System.out::println);

        /**
         * 只要提供的数据元素满足了谓词， anyMatch 操作就会返回 true 。对于第二个
         * 传递 "A2" 的元素，它的结果为真。由于数据流的链式调用是垂直执行
         * 的， map 这里只需要执行两次。所以 map 会执行尽可能少的次数，而不是把所有
         * 元素都映射一遍
         */
        Stream.of("d2", "a2", "b1", "b3", "c").map(s -> {
            System.out.println("map " + s);
            return s.toUpperCase();
        }).anyMatch(s -> {
            System.out.println("anyMatch " + s);
            return s.startsWith("A");
        });

        //map 和 filter 会对底层集合的每个字符串调用五次，
        //而 forEach 只会调用一次
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("A");
                })
                .forEach(s -> System.out.println("forEach: " + s));

        //我们调整操作顺序，将 filter 移动到调用链的顶端，就可以极大减少操作的
        //执行次数
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));


        //sorted 对输入集合中每个元素的多种组合调用了八次
        Stream.of("d2", "a2", "b1", "b3", "c")
                .sorted((s1, s2) -> {
                    System.out.printf("sort: %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));

        //sorted 永远不会调用，因为 filter 把输入集合减少至只有一个元
        //素。所以对于更大的输入集合会极大提升性能
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .sorted((s1, s2) -> {
                    System.out.printf("sort: %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));
    }
}
