package com.xym.myJava.lambda;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-14 9:38
 */
public class LambdaDemo8 {
    public static void main(String[] args) {
        /////////////////////////stream流不能被复用
        Stream<String> stream =
                Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));
        stream.anyMatch(s -> true); // ok
        //java.lang.IllegalStateException: stream has already been operated upon or closed
        //stream.noneMatch(s -> true); // exception

        //可以采用Supplier为我们创建可复用的数据流
        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> s.startsWith("a"));
        //每次对 get() 的调用都构造了一个新的数据流，我们将其保存来调用终止操作
        System.out.println(streamSupplier.get().anyMatch(s -> true));
        System.out.println(streamSupplier.get().noneMatch(s -> true));
    }
}
