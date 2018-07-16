package com.xym.myJava;

import java.util.Arrays;
import java.util.List;

/**
 * @author xym
 */
public class CollectPerson {
    public static void main(String[] args) {

        List<Person> peoples = Arrays.asList(new Person("zhangsan", 18), new Person("wangwu", 21), new Person("wangshuai", 23), new Person("wyl", 23), new Person("lisi", 28), new Person("admin", 30));

      /*  List<Person> w = peoples.stream().filter(p -> p.name.startsWith("w")).collect(Collectors.toList());
        System.out.println(w);*/
        /*Set<Person> w = peoples.stream().filter(p -> p.name.startsWith("w")).collect(Collectors.toSet());
        System.out.println(w);*/

        /**
         * 按年龄分组
         */
        /*Map<Integer, List<Person>> collect = peoples.stream().collect(Collectors.groupingBy(p -> p.age));
        collect.forEach((age, p) -> System.out.println(String.format("%s--%s", age, p)));*/

        /**
         * 所有人的平均年龄
         */
//        Double collect = peoples.stream().collect(Collectors.averagingInt(p -> p.age));
//        System.out.println(collect);

        /**
         * 概要统计器
         */
//        IntSummaryStatistics collect = peoples.stream().collect(Collectors.summarizingInt(p -> p.age));
//        System.out.println(collect);

       /* String collect = peoples.stream().filter(p -> p.age > 23).map(s -> s.name).collect(Collectors.joining(" and ", "年龄大于23岁以上的人有：", "被老板给开除了"));

        System.out.println(collect);*/

        /**
         * 自定义个collect
         */
/*
        Collector<Person, StringJoiner, String> personNameCollector = Collector.of(() -> new StringJoiner("|"), (j, p) -> j.add(p.name.toUpperCase()), (j1, j2) -> j1.merge(j2), StringJoiner::toString);

        String collect = peoples.stream().collect(personNameCollector);
        System.out.println(collect);*/


        /**
         * 使用reduce计算出最老的人
         */
//        peoples.stream().reduce((p1, p2) -> p1.age > p2.age ? p1 : p2).ifPresent(System.out::println);


        /**
         * 聚合构造新对象
         */
        /*Person reduce = peoples.stream().reduce(new Person("", 0), (p1, p2) -> {
            p1.age += p2.age;
            p1.name += p2.name;
            return p1;
        });
        System.out.println(reduce.name + "---" + reduce.age);*/



    }

    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
