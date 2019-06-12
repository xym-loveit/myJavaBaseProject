package com.xym.myJava.lambda2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-06-10 14:44
 */
public class StreamDemo2 {
    public static void main(String[] args) {
        List<String> myList =
                Arrays.asList("a1", "a2", "b1", "c2", "c1");
        //filter--过滤
        //map--转换
        //sorted--排序
        //forEach--遍历
        //filter 、 map 和 sorted 都是衔接操作，
        //而 forEach 是终止操作
        myList.stream().filter((s) -> s.startsWith("c")).map((s) -> s.toUpperCase()).sorted().forEach(System.out::println);
        Stream<String> stringStream = Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("b");
                });
        System.out.println(stringStream);
    }
}
