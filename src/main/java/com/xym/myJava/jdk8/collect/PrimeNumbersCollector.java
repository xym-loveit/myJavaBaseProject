package com.xym.myJava.jdk8.collect;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

/**
 * 质数收集器（加强版）
 * <p>
 * Collector泛型说明：
 * <p>
 * T：源元素类型
 * A：累加器类型
 * R：结果集类型
 *
 * @author xym
 * @create 2019-07-09 10:23
 */
public class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>() {{
            put(Boolean.TRUE, new ArrayList<>());
            put(Boolean.FALSE, new ArrayList<>());
        }};
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
            //经典的累加器
            //isPrime(acc.get(Boolean.TRUE), candidate)，判断当前数字是否为质数
            //如果是则加入原acc的key为true的list中，反之加入key为false的list中
            acc.get(isPrime(acc.get(Boolean.TRUE), candidate)).add(candidate);
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> acc1, Map<Boolean, List<Integer>> acc2) -> {
            acc1.get(Boolean.TRUE).addAll(acc2.get(Boolean.TRUE));
            acc1.get(Boolean.FALSE).addAll(acc2.get(Boolean.FALSE));
            return acc1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
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
}
