package com.xym.myJava.lambda;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-18 14:51
 */
public class ReduceDemo2 {
    public static void main(String[] args) {


        //Java8支持三种不同类型的reduce方法。第一种将流中的元素归约为流中的一个元素
        Set<Foo> collect = IntStream.range(1, 4)./*构建循环*/
                mapToObj(i -> new Foo("foo" + i * i)).collect(Collectors.toSet());
        collect.stream().reduce((f1, f2) -> {
            return f2.name.compareTo(f1.name) > 1 ? f1 : f2;
        }).ifPresent(System.out::println);

        //第二个reduce方法接受一个初始值，和一个BinaryOperator累加器。这个方法可以用于从流中的其它Foo对象中构造带有聚合后名称的Foo对象
        Foo reduce = collect.stream().reduce(new Foo(""), (f1, f2) -> {
            f1.name += f2.name;
            return f1;
        });

        //第三个reduce对象接受三个参数：初始值，BiFunction累加器和BinaryOperator类型的组合器函数。
        // 由于初始值的类型不一定为Foo，我们可以使用这个归约函数来连接所有Foo的name
        Optional.ofNullable(reduce).ifPresent(System.out::println);
        String reduce1 = collect.stream().reduce("", (strSum, foo) -> strSum += foo.name, (sum1, sum2) -> sum1 + sum2);
        Optional.ofNullable(reduce1).ifPresent(System.out::println);


    }
}
