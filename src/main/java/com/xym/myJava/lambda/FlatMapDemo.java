package com.xym.myJava.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-18 14:12
 */
public class FlatMapDemo {
    public static void main(String[] args) {

        List<Foo> foos = new ArrayList<>();
        IntStream.range(1, 4).forEach(i -> {
            foos.add(new Foo("foo" + i * i));
        });

        foos.stream().forEach(foo -> {
            IntStream.range(1, 4).forEach(i -> {
                foo.bars.add(new Bar("bar" + i + "-->" + foo.name));
            });
        });

        System.out.println("========================");
        foos.stream().forEach(foo -> {
            System.out.println(foo.name);
            foo.bars.stream().forEach(bar -> {
                System.out.println("\t" + bar.name);
            });
        });

        //扁平化处理
        foos.stream().flatMap(foo -> foo.bars.stream()).forEach(System.out::println);

        System.out.println("综合以上操作==");
        //peek还是返回源流，不改变源流
        IntStream.range(1, 4)./*构建循环*/
                mapToObj(i -> new Foo("foo" + i * i))./*循环构建外层foo*/
                peek(foo ->
                IntStream.range(1, 4).
                        mapToObj(i -> new Bar("bar" + i + "-->" + foo.name))./*循坏构建内层bar*/
                        forEach(foo.bars::add))./*循环将内层bar添加到外层foo*/
                flatMap(foo -> foo.bars.stream())./*扁平化处理foo里面的bar*/
                forEach(bar -> System.out.println(bar.name));
    }
}
