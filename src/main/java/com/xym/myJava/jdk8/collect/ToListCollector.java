package com.xym.myJava.jdk8.collect;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 自定义收集器
 *
 * @author xym
 * @create 2019-07-08 23:06
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    /**
     * 供应器
     *
     * @return
     */
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    /**
     * 累加器
     *
     * @return
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    /**
     * 合并器
     *
     * @return
     */
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    /**
     * 最终转换器
     *
     * @return
     */
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    /**
     * 特性集
     *
     * @return
     */
    @Override
    public Set<Characteristics> characteristics() {
        //UNORDERED ——归约结果不受流中项目的遍历和累积顺序的影响
        //CONCURRENT —— accumulator 函数可以从多个线程同时调用，且该收集器可以并行归
        //约流。如果收集器没有标为 UNORDERED ，那它仅在用于无序数据源时才可以并行归约
        //IDENTITY_FINISH ——这表明完成器方法返回的函数是一个恒等函数，可以跳过。这种
        //情况下，累加器对象将会直接用作归约过程的最终结果。这也意味着，将累加器 A 不加检
        //查地转换为结果 R 是安全的
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
    }
}
