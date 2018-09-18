package com.xym.myJava.lambda;

import java.util.Optional;

/**
 * flatMap组合Optional使用，可以处理null异常
 *
 * @author xym
 * @create 2018-09-18 14:37
 */
public class OptionalAndFlatMap {
    public static void main(String[] args) {

        //多个对象嵌套级联操作，并不会产生空指针异常
        Optional.ofNullable(Optional.of(new Outer()).
                flatMap(outer -> {
                    System.out.println("flatMap1");
                    return Optional.ofNullable(outer.nested);
                }).
                flatMap(nested -> {
                    System.out.println("flatMap2");
                    return Optional.ofNullable(nested.inner);
                }).
                flatMap(inner -> {
                    System.out.println("flatMap3");
                    return Optional.ofNullable(inner.foo);
                }).
                orElse("null--")).ifPresent(System.out::println);

    }
}
