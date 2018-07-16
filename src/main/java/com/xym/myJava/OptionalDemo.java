package com.xym.myJava;

import java.util.Optional;

/**
 * @author xym
 */
public class OptionalDemo {
    public static void main(String[] args) {
        Optional<String> optional = Optional.of("aaa");
        System.out.println(optional.get());
//        Optional<String> optional2 = Optional.of(null);
        Optional<String> optiona3 = Optional.ofNullable(null);
        Optional<String> optiona4 = Optional.ofNullable("value is null");
//        System.out.println(optiona3.get());

        if (optiona3.isPresent()) {
            System.out.println(optiona3.get());
        } else {
            System.out.println("optiona3 is null");
        }

        optiona3.ifPresent(System.out::println);
        optiona4.ifPresent(x -> System.out.println("x=" + x));

        /**
         * 静态指定默认值
         */
        System.out.println(optiona3.orElse("There is no value present!"));
        System.out.println(optiona4.orElse("There is no value present!"));

        /**
         * 动态生成默认值
         */
        System.out.println(optiona3.orElseGet(() -> "Default Value"));
        System.out.println(optiona4.orElseGet(() -> "Default Value"));

//        optiona3.orElseThrow(IllegalArgumentException::new);

        Optional<String> upperName = optiona3.map((value) -> value.toUpperCase());
//        System.out.println("--"+upperName.get());


    }
}
