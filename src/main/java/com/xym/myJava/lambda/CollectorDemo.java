package com.xym.myJava.lambda;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.IntStream;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-18 11:51
 */
public class CollectorDemo {
    public static void main(String[] args) {

        /**
         * 自定义Collector（供应器+累加器+组合器+终止器）
         *                                     Supplier<A> supplier,
         *                                     BiConsumer<A, T> accumulator,
         *                                     BinaryOperator<A> combiner,
         *                                     Function<A, R> finisher,
         */
        Collector<Person, StringJoiner, String> personStringJoinerStringCollector =
                Collector.of(
                        () -> new StringJoiner("|"),/*供应器*/
                        (joiner, p) -> joiner.add(p.name),/*累加器*/
                        StringJoiner::merge,/*组合器*/
                        StringJoiner::toString/*终止器*/);

        List<Person> personList = new ArrayList<>();
        IntStream.range(1, 10).forEach((i) -> {
            personList.add(new Person("zhangsan" + i, i));
        });


        /**
         * filter->map->sorted->collect
         *
         * 通过filter将name里面偶数名称的person过滤，使用map将person的name转为大写，通过sorted进行倒序，
         *
         * 最后将结果使用自定义collect收集起来
         */
        Optional.ofNullable(personList.stream().filter(person -> {
            System.out.println("==filter==");
            return Integer.parseInt(person.name.substring(person.name.length() - 1)) % 2 == 0;
        }).map(person -> {
            System.out.println("==map==");
            person.name = person.name.toUpperCase();
            return person;
        }).sorted((p1, p2) -> {
            System.out.println("==sorted==");
            return p2.name.compareTo(p1.name);
        }).collect(personStringJoinerStringCollector)).ifPresent(System.out::println);
    }
}
