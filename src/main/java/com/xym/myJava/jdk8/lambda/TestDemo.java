package com.xym.myJava.jdk8.lambda;

import java.util.concurrent.Callable;
import java.util.function.*;

/**
 * 一些jdk自带的函数式接口使用
 *
 * @author xym
 * @create 2019-06-19 17:13
 */
public class TestDemo {
    public static void main(String[] args) {
        //processExcep();
        //biMethod();
        //biFun();
        //binaryOperator();
        //biPredicate();
        //booSupplier();
        //doubleFun();
        UnaryOperator<Integer> unaryOperator = (i) -> i * i;
        System.out.println(unaryOperator.apply(10));
        System.out.println(unaryOperator.andThen((i) -> i + i).apply(10));

    }

    private static void doubleFun() {
        DoubleBinaryOperator binaryOperator = (a, b) -> Double.max(a, b);
        System.out.println(binaryOperator.applyAsDouble(10.0, 11.0));
        DoubleToIntFunction doubleToIntFunction = (a) -> Double.valueOf(a).compareTo(10.0);
        System.out.println(doubleToIntFunction.applyAsInt(11));
        DoubleUnaryOperator doubleUnaryOperator = (d) -> Double.valueOf(d * 2);
        System.out.println(doubleUnaryOperator.applyAsDouble(10));
        //先执行doubleUnaryOperator的lambda--->(d) -> Double.valueOf(d * 2)，再将结果带入andThen里面执行
        //5*2=10,10-2=8
        System.out.println(doubleUnaryOperator.andThen(d -> d - 2).applyAsDouble(5));
        //先执行compose的lambda--->(d) -> d - 2，再将结果带入doubleUnaryOperator的lambda--->(d) -> Double.valueOf(d * 2)里面执行
        //5-2=3,3*2=6
        System.out.println(doubleUnaryOperator.compose(d -> d - 2).applyAsDouble(5));
    }

    private static void booSupplier() {
        BooleanSupplier booleanSupplier = () -> true;
        BooleanSupplier booleanSupplier1 = () -> 1 > 2;
        BooleanSupplier booleanSupplier2 = () -> !false;
    }

    private static void biPredicate() {
        BiPredicate<String, String> biPredicate = String::equals;
        BiPredicate<Integer, Integer> integerBiPredicate = (a, b) -> {
            System.out.println("1---a=" + a + ",b=" + b);
            return a > b;
        };
        System.out.println(biPredicate.test("a", "b"));
        //negate为相反
        System.out.println(integerBiPredicate.negate().test(10, 1));
        //or可以连接多个条件，如果有一个满足，则后面的将短路，不执行
        BiPredicate<Integer, Integer> or = integerBiPredicate.or((a, b) -> {
            System.out.println("2---a=" + a + ",b=" + b);
            return a * b > 100;
        });
        System.out.println(or.test(50, 10));
    }

    private static void binaryOperator() {
        //针对2个相同类型的数据处理，返回他们一样的类型
        BinaryOperator<String> concat = (s1, s2) -> String.join("+", s1, s2);
        System.out.println(concat.apply("Hello", "World"));
    }

    private static void biFun() {
        BiFunction<Integer, Integer, Integer> biFunction = (Integer a, Integer b) -> a + b;
        System.out.println(biFunction.apply(10, 20));
        BiFunction<Integer, Integer, Integer> andThen = biFunction.andThen((sum) -> sum - 10);
        System.out.println(andThen.apply(10, 20));
    }

    private static void biMethod() {
        BiConsumer biConsumer = (v1, v2) -> System.out.println(v1 + "-" + v2);
        //biConsumer.accept("Hello", "World");
        BiConsumer biConsumer1 = biConsumer.andThen((o, o2) -> System.out.println(o + "-2222->" + o2));
        //biConsumer1.accept("Hello", "World");
        BiConsumer biConsumer2 = biConsumer1.andThen((o, o2) -> System.out.println(o + "-333->" + o2));
        biConsumer2.accept("Hello", "World");
    }

    /**
     * 这种带有检查性异常的lambda表达式，只能将异常捕获或者赋值给带有异常声明的接口方法
     */
    private static void processExcep() {
        Runnable runnable = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Callable<Void> runnable1 = () -> {
            Thread.sleep(1000);
            return null;
        };
    }
}
