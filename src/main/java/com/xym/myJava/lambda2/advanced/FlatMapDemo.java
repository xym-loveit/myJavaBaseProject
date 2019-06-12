package com.xym.myJava.lambda2.advanced;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.IntStream;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-06-10 18:09
 */
public class FlatMapDemo {
    public static void main(String[] args) {
        List<FlatMapDemo.Foo> foos = new ArrayList<>();
        IntStream
                .range(1, 4)
                .forEach(i -> foos.add(new Foo("Foo" + i)));
        System.out.println(foos);
        // create bars
        foos.forEach(f ->
                IntStream
                        .range(1, 4)
                        .forEach(i -> f.bars.add(new Bar("Bar" + i + " <- " + f.
                                name))));
        for (Foo foo : foos) {
            System.out.println(foo.name + "------------------------>");
            for (Bar bar : foo.bars) {
                System.out.println(new StringJoiner("#").add(bar.name));
            }
        }
        //flatMap可以用来多级操作[3个foo，每一个里面有3个bar，扁平化处理可以将结果展开为9个bar的stream]
        foos.stream().flatMap((f) -> f.bars.stream()).forEach((b) -> System.out.println(b.name));
        System.out.println("------------------------------------->");
        IntStream.range(1, 4).
                mapToObj((i) -> new Foo("Foo" + i)).
                //创建Foo
                        peek((f) -> IntStream.range(1, 4).
                        //创建bar
                                mapToObj((i) -> new Bar("Bar" + i + " <- " + f.name)).
                        //关联
                                forEach(f.bars::add)).
                //flat处理（扁平化处理）
                        flatMap(foo -> foo.getBars().stream()).forEach(bar -> System.out.println(bar.name));


        //多层级联是否为空
        Outer outer = new Outer();
        if (outer != null && outer.nested != null && outer.nested.inner
                != null) {
            System.out.println(outer.nested.inner.foo);
        }

        //替代多层级联是否为空
        Optional.of(new Outer()).
                flatMap((o) -> {
                    System.out.println("Optional-nested");
                    return Optional.ofNullable(o.nested);
                }).
                flatMap((n) -> {
                    System.out.println("Optional-inner");
                    return Optional.ofNullable(n.inner);
                }).
                flatMap(i -> {
                    System.out.println("Optional-foo");
                    return Optional.ofNullable(i.foo);
                }).
                ifPresent(System.out::println);

    }

    static class Foo {
        String name;
        List<Bar> bars = new ArrayList<>();

        Foo(String name) {
            this.name = name;
        }

        public List<Bar> getBars() {
            return bars;
        }
    }

    static class Bar {
        String name;

        Bar(String name) {
            this.name = name;
        }
    }


    /**************************************三层级联关系的对象嵌套****************************/
    static class Outer {
        Nested nested;
    }

    static class Nested {
        Inner inner;
    }

    static class Inner {
        String foo;
    }
}
