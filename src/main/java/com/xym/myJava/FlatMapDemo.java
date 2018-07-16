package com.xym.myJava;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author xym
 */
public class FlatMapDemo {

    public static void main(String[] args) {
       /* List<Foo> foos = new ArrayList<>();
        IntStream.range(1, 4).forEach(i -> foos.add(new Foo("Foo" + i)));


        foos.forEach(f -> IntStream.range(1, 4).forEach(i -> f.bars.add(new Bar("Bar" + i + "<-" + f.name))));

        foos.stream().flatMap(f -> f.bars.stream()).forEach(b -> System.out.println(b.name));*/


        /**
         * 使用流式操作的单一流水线,niubility
         */
       /* IntStream.range(1, 4).mapToObj
                (i -> new Foo("Foo" + i)).peek
                (f -> IntStream.range(1, 4).mapToObj(
                        i -> new Bar("Bar" + i + "<-" + f.name)
                ).forEach(f.bars::add)).flatMap(f -> f.bars.stream()).forEach(b -> System.out.println(b.name));*/

        /**
         * 原始的null判断
         */
        Outer outer = new Outer();
        if (outer != null && outer.nested != null && outer.nested.inner != null) {
            System.out.println(outer.nested.inner.foo);
        }

        /**
         * 可以使用Optional实现和上面相同的行为
         */
        Optional.of(new Outer()).flatMap(o -> Optional.ofNullable(o.nested)).flatMap(n -> Optional.ofNullable(n.inner)).flatMap(i -> Optional.ofNullable(i.foo)).ifPresent(System.out::println);

    }

    static class Outer {
        Nested nested;
    }

    static class Nested {
        Inner inner;
    }

    static class Inner {
        String foo;
    }


    static class Foo {
        private String name;

        private List<Bar> bars = new ArrayList<>();

        public Foo(String name) {
            this.name = name;
        }
    }

    static class Bar {
        private String name;

        public Bar(String name) {
            this.name = name;
        }
    }

}
