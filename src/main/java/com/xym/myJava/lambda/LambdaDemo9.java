package com.xym.myJava.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-14 9:38
 */
public class LambdaDemo9 {
    public static void main(String[] args) {
        List<Person> persons =
                Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));

        //流的元素中构造了一个列表。如果需要
        //以 Set 来替代 List ，只需要使用 Collectors.toSet() 就好了
        System.out.println(persons.stream().filter(p -> p.name.startsWith("P")).collect(Collectors.toList()));


        //按年龄进行分组,注意groupingBy的类型Map<Integer, List<Person>>>
        persons.stream().collect(Collectors.groupingBy(p -> p.age)).forEach((k, v) -> System.out.format("%d=%s\n", k, v));
        //计算所有人的平均年龄
        System.out.println(persons.stream().collect(Collectors.averagingInt(p -> p.age)));
        //可以返回一个概要统计对象：IntSummaryStatistics{count=4, sum=76, min=12, average=19.000000, max=23}
        //概要统计对象封装了一些统计数据:count为个数、sum为总和、min最小、max最大、average为平均
        System.out.println(persons.stream().collect(Collectors.summarizingInt(p -> p.age)));
        //将所有人连接为一个字符串，joining第一个参数为连接符，第二个为前缀，第三个为后缀
        String joinStr = persons.stream().filter(person -> person.age > 18).map(p -> p.name).collect(Collectors.joining(" and ", "上届优秀员工有 ", "请大家向他们学习！"));
        System.out.println(joinStr);

        //自定义，创建一个收集器
        //由于Java中的字符串是不可变的，我们需要一个助手类 StringJointer 。让收集
        //器构造我们的字符串。供应器最开始使用相应的分隔符构造了这样一
        //个 StringJointer 。累加器用于将每个人的大写名称加到 StringJointer 中。
        //组合器知道如何把两个 StringJointer 合并为一个。最后一步，终结器
        //从 StringJointer 构造出预期的字符串
        Collector<Person, StringJoiner, String> personStringJoinerStringCollector = Collector.of(
                () -> new StringJoiner("|"),//供应器
                (j, p) -> j.add(p.name.toUpperCase())/*累加器*/, (j1, j2) -> j1.merge(j2)/*组合器*/, StringJoiner::toString/*终止器*/);


        System.out.println(persons.stream().collect(personStringJoinerStringCollector));
    }
}

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }
}