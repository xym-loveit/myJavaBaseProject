package com.xym.myJava.jdk8.stream;


import com.xym.myJava.jdk8.Person;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-06-11 11:40
 */
public class StreamDemo2 {
    public static void main(String[] args) {
        List<Person> persons =
                Arrays.asList(
                        new Person(101, "Max", 18),
                        new Person(102, "Peter", 23),
                        new Person(103, "Pamela", 23),
                        new Person(104, "David", 12));
        Map<Long, String> collect = persons.stream().collect(Collectors.toMap(Person::getId, Person::getName));
        System.out.println(collect);
        //真正会应用到的
        Map<Long, Person> collect1 = persons.stream().collect(Collectors.toMap(Person::getId, Function.identity()));
        System.out.println(collect1);
        Stream<Locale> availableLocales = Stream.of(Locale.getAvailableLocales());
        availableLocales.collect(Collectors.toMap((l) -> {
            System.out.println(l);
            return l.getDisplayLanguage();
        }, (l) -> {
            System.out.println(l.getDisplayLanguage(l));
            return l.getDisplayLanguage(l);
        }, (existingValue, newValue) -> existingValue));
        Stream<Locale> availableLocales2 = Stream.of(Locale.getAvailableLocales());
        //获取一个国家的所有语言
        Map<String, Set<String>> collect2 = availableLocales2.collect(Collectors.toMap(
                (l) -> l.getDisplayCountry(),
                (l) -> Collections.singleton(l.getDisplayLanguage()),
                (a, b) -> {
                    Set<String> r = new HashSet<>(a);
                    r.addAll(b);
                    return r;
                }
        ));
        System.out.println(collect2);
        //分组
        Stream<Locale> availableLocales3 = Stream.of(Locale.getAvailableLocales());
        //按国家分组语言
        Map<String, List<Locale>> collect3 = availableLocales3.collect(Collectors.groupingBy(Locale::getCountry));
        System.out.println(collect3);
        System.out.println(collect3.get("CH"));
        //englishAndOTherLocals=
        Map<Boolean, List<Locale>> englishAndOtherLocales = Stream.of(Locale.getAvailableLocales()).collect(Collectors.partitioningBy(l -> l.getLanguage().equals("en")));
        System.out.println(englishAndOtherLocales.get(true));
        //计算每个国家拥有多少种语言
        System.out.println(Stream.of(Locale.getAvailableLocales()).collect(Collectors.groupingBy(Locale::getCountry, Collectors.counting())));

    }
}
