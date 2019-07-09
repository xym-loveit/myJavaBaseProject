package com.xym.myJava.jdk8.collect;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-07-08 13:59
 */
public class CollectDemo {
    public static void main(String[] args) {
        //reducing();
        //group01();
        //group02();
        //group03();
        //prime100();
        //collector01();

        //performance();

    }

    /**
     * 自定义收集器性能测试
     */
    private static void performance() {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            partitionPrimes(1_000_000);
            //prime(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) {
                fastest = duration;
            }
        }
        System.out.println(
                "Fastest execution done in " + fastest + " msecs");
    }


    private static void partitionPrimes(int n) {
        for (List<Integer> value : IntStream.rangeClosed(2, n).boxed().collect(new PrimeNumbersCollector()).values()) {
            System.out.println(value.size());
        }
    }


    /**
     * 是否为质数的加强版（通过使用比被测试数字更小的质数判断，进一步缩减判定范围）
     *
     * @param primes
     * @param candidate
     * @return
     */
    public static boolean isPrime(List<Integer> primes, int candidate) {
        //被检查数的开方，缩减判定范围,提高效率
        int candidateRoot = (int) Math.sqrt((double) candidate);
        //把之前筛选出来的元素列表满足takeWhile函数第二个参数（谓词：满足比被测试数更小的质数）所有元素作为除数（所有都是质数）
        return takeWhile(primes, i -> i <= candidateRoot)
                .stream()
                .noneMatch(p -> candidate % p == 0);
    }


    /**
     * 给定一个排序列表和谓词，返回元素满足谓词的最长子列表
     *
     * @param list
     * @param p
     * @param <A>
     * @return
     */
    private static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;
        for (A a : list) {
            //检查当前列表中项目是否满足谓词
            if (!p.test(a)) {
                //如果谓词不满足返回该项目之前的前缀子列表
                return list.subList(0, i);
            }
            i++;
        }
        //当列表中所有项目都满足谓词则返回列表本身
        return list;
    }

    private static void collector01() {
        System.out.println(getCollect());
        ArrayList<Object> collect = Dish.menu.stream().collect(ArrayList::new, List::add, List::addAll);
        System.out.println(collect);
    }

    private static List<Dish> getCollect() {
        List<Dish> collect = Dish.menu.stream().collect(new ToListCollector<>());
        return collect;
    }

    /**
     * 求100之间的质数
     */
    private static void prime100() {
        int n = 100;
        Map<Boolean, List<Integer>> collect = IntStream.rangeClosed(2, n).boxed().collect(Collectors.partitioningBy(CollectDemo::isPrime));
        System.out.println(collect);
    }

    /**
     * 求100之间的质数
     */
    private static void prime(int n) {
        for (List<Integer> value : IntStream.rangeClosed(2, n).boxed().collect(Collectors.partitioningBy(CollectDemo::isPrime)).values()) {
            System.out.println(value.size());
        }
        //System.out.println(collect);
    }

    /**
     * 是否为质数
     *
     * @param candidate
     * @return
     */
    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(value -> candidate % value == 0);
    }

    private static void group03() {
        //按素食和非素食分组
        Map<Boolean, List<Dish>> collect = Dish.menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
        System.out.println(collect);
        //2级map
        Map<Boolean, Map<Dish.Type, List<Dish>>> collect1 = Dish.menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.groupingBy(Dish::getType)));
        System.out.println(collect1);
        //素食和非素食中热量最高的菜
        Map<Boolean, Dish> collect2 = Dish.menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println(collect2);
    }

    private static void group02() {
        System.out.println(Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting())));
        //按类型分组后，求出分组中卡路里最大的菜
        Map<Dish.Type, Optional<Dish>> collect = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println(collect);
        //去掉option
        Map<Dish.Type, Dish> collect1 = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println(collect1);
        Map<Dish.Type, Integer> collect2 = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.summingInt(Dish::getCalories)));
        System.out.println(collect2);
        //每个分组中包含的能量等级
        Map<Dish.Type, Set<Dish.CaloricLevel>> collect3 = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(dish -> {
            if (dish.getCalories() <= 400) {
                return Dish.CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return Dish.CaloricLevel.NORMAL;
            } else {
                return Dish.CaloricLevel.FAT;
            }
        }, Collectors.toCollection(HashSet::new))));
        System.out.println(collect3);
    }

    private static void group01() {
        //按类型进行分组
        System.out.println(Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType)));
        Map<Dish.CaloricLevel, List<Dish>> collect = Dish.menu.stream().collect(Collectors.groupingBy(dish -> {
            if (dish.getCalories() <= 400) {
                return Dish.CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return Dish.CaloricLevel.NORMAL;
            } else {
                return Dish.CaloricLevel.FAT;
            }
        }));
        System.out.println(collect);
        //2级分组，相当于一个表
        Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> mapMap = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(dish -> {
            if (dish.getCalories() <= 400) {
                return Dish.CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return Dish.CaloricLevel.NORMAL;
            } else {
                return Dish.CaloricLevel.FAT;
            }
        })));
        System.out.println(mapMap);
    }

    private static void reducing() {
        //base();
        //求和：使用广义的归约函数(高级形式)
        //三个参数：1、归约操作的起始值，也是流中没有元素时的返回值
        //2、转换函数，将对象转为int
        //3、 BinaryOperator ，将两个项目累积成一个同类型的值（如2个值求和）
        Integer sum = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.reducing(0, Integer::intValue, Integer::sum));
        System.out.println("sum=" + sum);
        //最大值
        Optional<Integer> collect = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.reducing(Integer::max));
        System.out.println(collect.get());
        //最简洁的方法
        System.out.println(IntStream.rangeClosed(1, 100).sum());
    }

    private static void base() {
        //count
        System.out.println(IntStream.rangeClosed(1, 100).boxed().count());
        System.out.println(IntStream.rangeClosed(1, 100).boxed().collect(Collectors.counting()));
        //max
        System.out.println(IntStream.rangeClosed(1, 100).boxed().reduce(Integer::max));
        IntStream.rangeClosed(1, 100).boxed().collect(Collectors.maxBy(Comparator.comparingInt(Integer::intValue)));
        //summingInt求和
        System.out.println(IntStream.rangeClosed(1, 100).boxed().collect(Collectors.summingInt(Integer::intValue)));
        //avg
        Double collect = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.averagingInt(Integer::intValue));
        System.out.println(collect);
        //统计概要
        IntSummaryStatistics statistics = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println(statistics);
        //str join
        String collect1 = IntStream.rangeClosed(1, 100).boxed().map(String::valueOf).collect(Collectors.joining("#"));
        System.out.println(collect1);
    }
}
