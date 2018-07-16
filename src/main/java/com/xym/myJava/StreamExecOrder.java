package com.xym.myJava;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author xym
 */
public class StreamExecOrder {
    public static void main(String[] args) {
//        Stream.of("a", "b", "c", "d", "e", "f").filter(n -> {
//            System.out.println("filter=" + n);
//            return true;
//        }).forEach(n -> System.out.println("forEach=" + n));


        /*Stream.of("a", "b", "c", "d", "e", "f").map(s -> {
            System.out.println(s);
            return s.toUpperCase();
        }).anyMatch(s -> {
            System.out.println(s);
            return s.startsWith("C");
        });*/

        Stream<String> stringStream = Stream.of("a", "b", "c", "d").filter(n -> n.startsWith("c"));
//        stringStream.anyMatch(n -> true);
//        stringStream.noneMatch(n -> true);


        Supplier<Stream<String>> streamSupplier = () -> Stream.of("a", "b", "c", "d").filter(n -> n.startsWith("c"));

        boolean b = streamSupplier.get().anyMatch(n -> true);
        boolean b1 = streamSupplier.get().noneMatch(n -> true);
        System.out.println(b+"--"+b1);

    }
}
