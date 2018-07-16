package com.xym.myJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author xym
 */
public class PredicateDemo {
    public static void main(String[] args) {

        testSummaryStatistics();
//        testMap2();
//        testReduce();
//        testMap();
    }

    private static void testPredicateAnd() {
        List<String> names = new ArrayList<>(Arrays.asList("abcde", "name2", "Java", "Jacks2", "Jsop"));

        Predicate<String> p1 = (n) -> n.startsWith("J");
        Predicate<String> p2 = (n) -> n.length() == 4;

        /**
         * 使用and合并2个Predicate
         */
        names.stream().filter(p1.and(p2)).forEach(System.out::println);
    }

    private static void testMap() {
        List<Integer> lists = Arrays.asList(100, 200, 300, 400);

        lists.stream().map((i) -> i + i * .12).forEach(System.out::println);

    }

    private static void testMap2() {
        List<String> names = new ArrayList<>(Arrays.asList("abcde", "name2", "Java", "Jacks2", "Jsop"));

        /**
         * 将字符串转为小写并使用逗号连接
         */
        String collect = names.stream().map(x -> x.toLowerCase()).collect(Collectors.joining(","));
        System.out.println(collect);
    }

    private static void testReduce() {
        List<Integer> lists = Arrays.asList(100, 200, 300, 400);

        /**
         * 合并结果
         */
        Double aDouble = lists.stream().map((i) -> i + i * .12).reduce((sum, cost) -> sum + cost).get();
        System.out.println(aDouble);
    }

    private static void testSummaryStatistics() {
        List<Integer> lists = Arrays.asList(2, 3, 5, 7, 9, 15, 5, 30);
        /**
         * 计算集合元素最大值、最小值、总和、平均值
         */
        IntSummaryStatistics intSummaryStatistics = lists.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("max=" + intSummaryStatistics.getMax());
        System.out.println("min=" + intSummaryStatistics.getMin());
        System.out.println("sum=" + intSummaryStatistics.getSum());
        System.out.println("avg=" + intSummaryStatistics.getAverage());
    }
}
