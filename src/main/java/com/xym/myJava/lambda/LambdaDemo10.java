package com.xym.myJava.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * flatMap 将流中的每个元素，转换为其它对象的流。所以每个对象会被转换为零
 * 个、一个或多个其它对象，以流的形式返回。这些流的内容之后会放
 * 进 flatMap 所返回的流中
 *
 * @author xym
 * @create 2018-09-14 10:39
 */
public class LambdaDemo10 {
    public static void main(String[] args) {


        ////////////////////////////////////////////////////////////////////第一种操作原始操作
        List<Foo> foos = new ArrayList<>();

        for (int i = 1; i < 4; i++) {
            foos.add(new Foo("Foo" + i));
            List<Bar> bars = new ArrayList<>();
            for (int j = 1; j < 4; j++) {
                bars.add(new Bar("Bar " + (j) + "<- " + foos.get(i-1).name));
            }
            foos.get(i-1).bars = bars;
        }

        for (Foo foo : foos) {
            for (Bar bar : foo.bars) {
                System.out.println(bar);
            }
        }

        ///////////////////////////////////////////////////////////第二种操作--使用jdk8分段式操作

        foos.clear();

        //创建foo
        IntStream.range(1, 4).forEach(i -> foos.add(new Foo("Foo " + i)));
        //创建bar
        foos.forEach(f -> IntStream.range(1, 4).forEach(i -> f.bars.add(new Bar("Bar " + i + "<- " + f.name))));

        //foos.forEach(f -> System.out.println(f.bars));


        //foos.stream().flatMap(f -> f.bars.stream()).forEach(b -> System.out.println(b.name));

        //////////////////////////////////////////////////////////////第三种高级操作--简化后的流式操作，一行语句搞定
        foos.clear();

        //实现了foos、bars的创建，关联添加，及bars的遍历
        IntStream.range(1, 4).mapToObj(i -> new Foo("Foo" + i)).peek(f -> {
            IntStream.range(1, 4).mapToObj(i -> new Bar("Bar " + i + "<- " + f.name)).forEach(f.bars::add);
        }).flatMap(foo -> foo.bars.stream()).forEach((b)->{System.out.println(b);});
    }
}

class Foo {
    String name;
    List<Bar> bars = new ArrayList<>();

    Foo(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public List<Bar> getBars() {
        return bars;
    }
}

class Bar {
    String name;

    Bar(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}