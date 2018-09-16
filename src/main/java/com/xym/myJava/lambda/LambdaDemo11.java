package com.xym.myJava.lambda;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-14 11:32
 */
public class LambdaDemo11 {


    public static void main(String[] args) {
        Outer outer = new Outer();
        if (outer != null && outer.nested != null && outer.nested.inner != null) {
            System.out.println(outer.nested.inner.foo);
        }

        /*使用Optional.flatMap来操作，避免空指针*/
        Optional.of(new Outer()).
                flatMap(o -> {
                    System.out.println("nested");
                    return Optional.ofNullable(o.nested);
                }).
                flatMap(n -> {
                    System.out.println("inner");
                    return Optional.ofNullable(n.inner);
                }).
                flatMap(i -> {
                    System.out.println("last");
                    return Optional.ofNullable(i.foo);
                }).ifPresent(System.out::println);


        //构造Person集合
        List<Person> collect = IntStream.range(1, 4).mapToObj(i -> new Person("name " + i, (int) (Math.random() * 30))).collect(Collectors.toList());


        //使用reduce计算出最老的人
        collect.stream().peek((p) ->
                System.out.println(p.age + "--" + p.name)
        ).reduce((p1, p2) -> p1.age > p2.age ? p1 : p2).ifPresent(System.out::println);

        //使用聚合结果构造新对象,reduce第一个参数为新对象
        Person result = collect.stream().reduce(new Person("", 0), (p1, p2) -> {
            p1.age += p2.age;
            p1.name += p2.name;
            return p1;
        });

        //System.out.format("name=%s; age=%s", result.name, result.age);

        Integer sumAge = collect.stream().reduce(0, (sum, p) -> sum += p.age, (sum1, sum2) -> sum1 + sum2);
        System.out.println(sumAge);

        //调试一下
        Integer ageSum = collect
                .stream()
                .reduce(0,
                        (sum, p) -> {
                            System.out.format("accumulator: sum=%s; person=%s\n"
                                    , sum, p);
                            return sum += p.age;
                        },
                        (sum1, sum2) -> {
                            System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
                            return sum1 + sum2;
                        });

        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println(commonPool.getParallelism()); // 3
    }

}

/*多个对象嵌套操作*/
class Outer {
    Nested nested;
}

class Nested {
    Inner inner;
}

class Inner {
    String foo;
}
