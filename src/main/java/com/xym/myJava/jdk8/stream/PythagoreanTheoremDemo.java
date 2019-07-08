package com.xym.myJava.jdk8.stream;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 勾股定理
 *
 * @author xym
 * @create 2019-07-08 10:20
 */
public class PythagoreanTheoremDemo {
    public static void main(String[] args) {
        //t01();
        //t02();
        //t03();
    }

    private static void t03() {
        //先产生数组，再过滤，可以简化代码
        Stream<double[]> stream = IntStream.rangeClosed(1, 100).boxed().flatMap(a ->
                IntStream.rangeClosed(a, 100).boxed().map(b -> new double[]{a, b, Math.sqrt(a * a + b * b)}).filter(doubles -> doubles[2] % 1 == 0 && doubles[2] <= 100));
        stream.forEach(doubles -> {
            System.out.println(Arrays.toString(doubles));
        });
    }

    private static void t02() {
        //求1到100所有勾股数对
        //int a = 8;
        Stream<int[]> stream = IntStream.rangeClosed(1, 100).boxed().flatMap(a -> {
            //filter通过勾股定律作为筛选条件
            return IntStream.rangeClosed(a, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).
                    //boxed为将数值流转为普通流
                    //map将满足勾股定律的数值转为数组
                            boxed().map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});
        });
        StringJoiner joiner = new StringJoiner("\n", "[\n", "\n]");
        stream.limit(100).forEach(ints -> {
            //通过StringJoiner将数组输出美化，e.g--[(8,6,10),(8,15,17)]
            String intss = Arrays.stream(ints).mapToObj(String::valueOf).collect(Collectors.joining(",", "(", ")"));
            joiner.add(intss);
        });

        System.out.println(joiner);
    }

    /**
     * 筛选可以和一个固定的数字构成勾股数的（1----100）之间的数字
     * 即满足：a*a+b*b=c*c（Math.sqrt(a * a + b * b) % 1 == 0）
     */
    private static void t01() {
        int a = 9;
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        Stream<int[]> stream = IntStream.rangeClosed(1, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).
                boxed().map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});
        stream.forEach(ints -> {
            System.out.println("----------------");
            String collect = Arrays.stream(ints).mapToObj(String::valueOf).collect(Collectors.joining(",", "(", ")"));
            joiner.add(collect);
        });
        System.out.println(joiner.toString());
    }
}
