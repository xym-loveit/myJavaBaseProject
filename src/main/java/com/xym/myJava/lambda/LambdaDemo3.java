package com.xym.myJava.lambda;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-13 16:10
 */
public class LambdaDemo3 {
    public static void main(String[] args) {
        Predicate<String> predicate = (s) -> s.length() > 0;

        System.out.println(predicate.test("hello"));
        /*反面*/
        System.out.println(predicate.negate().test("test"));

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
        System.out.println(isEmpty.test(""));
        System.out.println(isNotEmpty.test(""));

        Function<String, Integer> toInteger = Integer::valueOf;
        /*2个组合使用*/
        Function<String, String> backToString = toInteger.andThen(String
                ::valueOf);


        System.out.println(backToString.apply("123"));

        Supplier<LambdaDemo2.Person> supplier = LambdaDemo2.Person::new;
        LambdaDemo2.Person person = supplier.get();
        System.out.println(person);

        Consumer<LambdaDemo2.Person> consumer = (p) -> System.out.println("Hello " + p.getFirstName() + "--------------");
        consumer.accept(new LambdaDemo2.Person("admin"));

        LambdaDemo2.Person zabbix = new LambdaDemo2.Person("Zabbix");
        LambdaDemo2.Person z = new LambdaDemo2.Person("12A");
        Comparator<LambdaDemo2.Person> comparator = (p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName());
        //reversed反向比较
        System.out.println(comparator.reversed().compare(zabbix, z));

        Optional<String> abc = Optional.of("abc");
        System.out.println(abc.isPresent());
        System.out.println(abc.get());
        System.out.println(abc.orElse("123"));

        //如果存在的话，执行
        abc.ifPresent((s) -> {
            System.out.println("-------------------" + s.charAt(0));
        });
    }
}
