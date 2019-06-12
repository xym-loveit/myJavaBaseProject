package com.xym.myJava.lambda2;

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
 * @create 2019-06-10 14:13
 */
public class LambdaDemo3 {
    public static void main(String[] args) {
        Predicate<String> predicate = (s) -> s.length() > 0;
        System.out.println(predicate.test(""));
        //取反
        System.out.println(predicate.negate().test(""));
        //方法引用
        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
        //功能方法
        Function<String, Integer> toInteger = Integer::valueOf;
        System.out.println(toInteger.apply("123").getClass());
        Function<String, String> backToString = toInteger.andThen(String
                ::valueOf);
        System.out.println(backToString.apply("678"));
        //生产型
        Supplier<LambdaDemo2.Person> personSupplier = LambdaDemo2.Person::new;
        personSupplier.get(); // new Person
        //Consumer为消费型
        Consumer<LambdaDemo2.Person> greeter = (p) -> System.out.println("Hello, " +
                p.firstName);
        greeter.accept(new LambdaDemo2.Person("Luke", "Skywalker"));

        Comparator<LambdaDemo2.Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);
        LambdaDemo2.Person p1 = new LambdaDemo2.Person("John", "Doe");
        LambdaDemo2.Person p2 = new LambdaDemo2.Person("Alice", "Wonderland");
        System.out.println(comparator.compare(p1, p2));
        System.out.println(comparator.reversed().compare(p1, p2));
        //值容器
        Optional<String> optional = Optional.of("bam");
        optional.isPresent(); // true
        optional.get(); // "bam"
        System.out.println(optional.orElse("fallback"));
        optional.ifPresent((s) -> System.out.println(s.charAt(0)));
    }
}
