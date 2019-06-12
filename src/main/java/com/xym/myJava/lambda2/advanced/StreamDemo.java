package com.xym.myJava.lambda2.advanced;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-06-10 17:48
 */
public class StreamDemo {
    public static void main(String[] args) {
        List<Person> persons =
                Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));

        //过滤结果为list
        List<Person> filtered = persons.stream().filter((p) -> p.name.startsWith("P")).collect(Collectors.toList());
        System.out.println(filtered);
        //过滤结果为set
        Set<Person> filtered2 = persons.stream().filter((p) -> p.name.startsWith("P")).collect(Collectors.toSet());
        System.out.println(filtered2);
        //按用户的年龄分组
        Map<Integer, List<Person>> collect = persons.stream().collect(Collectors.groupingBy((person -> person.age)));
        System.out.println(collect);
        //计算所有人的平均年龄
        System.out.println(persons.stream().collect(Collectors.averagingInt(p -> p.age)));
        //概要统计对象
        IntSummaryStatistics collect1 = persons.stream().collect(Collectors.summarizingInt((p) -> p.age));
        System.out.println(collect1);
        //将年龄大于18的所有人姓名链接为一个字符串
        String collect2 = persons.stream().filter(p -> p.age >= 18).map(person -> person.name).collect(Collectors.joining("#", "年龄大于18岁的人有 ", "你们成年了"));
        System.out.println(collect2);

    }
}
