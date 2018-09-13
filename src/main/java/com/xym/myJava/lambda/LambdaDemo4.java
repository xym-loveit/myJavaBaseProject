package com.xym.myJava.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-13 16:52
 */
public class LambdaDemo4 {
    public static void main(String[] args) {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        /**
         * stream包装为流
         * filter过滤,通过谓词
         * forEach遍历，为终结操作
         */
        stringCollection.stream().filter((s) -> s.startsWith("a")).forEach((s) -> System.out.println(s));
        /*sorted仅仅返回了一个排序的视图，并未改变原始集合中的元素顺序*/
        stringCollection.stream().sorted().filter(s -> s.startsWith("b")).forEach(s -> System.out.println(s));
        /*map可以将一种元素对应到另外一种对象*/
        stringCollection.stream().map(String::toUpperCase).sorted((s1, s2) -> s2.compareTo(s1)).forEach(s -> System.out.println(s));
        /*使用Comparator.reverseOrder()直接替换*/
        stringCollection.stream().map(String::toUpperCase).sorted(Comparator.reverseOrder()).forEach(s -> System.out.println(s));
        /*是否匹配其中任何一个,所有匹配操作都是终结操作*/
        System.out.println(stringCollection.stream().anyMatch(s -> s.startsWith("b")));
        System.out.println(stringCollection.stream().allMatch(s -> s.startsWith("c")));
        System.out.println(stringCollection.stream().noneMatch(s -> s.startsWith("z")));

        /*count操作返回流操作中的数量*/
        System.out.println(stringCollection.stream().filter(s -> s.startsWith("b")).count());
        /*reduce聚合操作，削减元素数量，返回optional*/
        Optional<String> reduce = stringCollection.stream().filter(s -> s.startsWith("b")).reduce((s, s2) -> s.concat("##").concat(s2));
        reduce.ifPresent(System.out::println);
    }
}
