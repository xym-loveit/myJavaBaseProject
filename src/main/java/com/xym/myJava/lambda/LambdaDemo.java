package com.xym.myJava.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-13 9:20
 */
public class LambdaDemo {
    public static void main(String[] args) {

        //use01();
        //use02();
        Runnable runnable = () -> {
            System.out.println("Running");
        };


        /**
         * 传入一个参数，无返回值
         */
        Consumer<Apple> consumer = (a) -> {
            System.out.println(a);
        };

        /**
         *funcation,传递一个参数，出去另外一个参数
         */
        Function<Apple, String> function = (a) -> {
            return a.getColor();
        };


        /**
         * Bi开头的以为一对(2个参数),传递2个入参
         */
        BiConsumer<String, String> biConsumer = (a, b) -> {
            System.out.println(a + "--" + b);
        };


        /**
         *传递2个String类型入参，返回一个Integer
         */
        BiFunction<String, String, Integer> biFunction = (a, b) -> {
            return a.length() + b.length();
        };

        /**
         * 传递2个Integer参数，返回boolean
         */
        BiPredicate<Integer, Integer> biPredicate = (a, b) -> a - b > 0;

        /**
         * 无参数，提供boolean
         */
        BooleanSupplier booleanSupplier = () -> Boolean.FALSE;
        BooleanSupplier booleanSupplier2 = () -> "".equals("123");

        /**
         * 2个double类型数操作
         */
        DoubleBinaryOperator doubleBinaryOperator = (a, b) -> {
            return a * b;
        };

        /**
         * double类型消费者
         */
        DoubleConsumer consumer1 = System.out::println;
        DoubleConsumer consumer2 = (a) -> System.out.println(a);


        /**
         *入参为double，出参为泛型指定，function的double类型版本
         */
        DoubleFunction<String> doubleFunction = (d) -> {
            return String.valueOf(d);
        };

        /**
         * Predicate类型的double版本
         */
        DoublePredicate doublePredicate = (d) -> {
            return Double.valueOf(d).intValue() > 2;
        };


        /**
         * Supplier类型的double版本
         */
        DoubleSupplier doubleSupplier = () -> {
            return Math.PI;
        };

        /**
         * Function的Double转为Int的版本
         */
        DoubleToIntFunction doubleToIntFunction = (d) -> {
            return Integer.parseInt(d + "");
        };

        /**
         * Function的Double转为long的版本
         */
        DoubleToLongFunction doubleToLongFunction = (d) -> {
            return Long.parseLong(d + "");
        };

        /**
         * 操作2个int入参，返回一个int
         */
        IntBinaryOperator intBinaryOperator = (a, b) -> {
            return a + b;
        };

    }

    private static void use02() {
        List<Apple> apples = Arrays.asList(new Apple("red", 150), new Apple("green", 120), new Apple("green", 120));
        List<Apple> apples1 = filterAppleByLambda(apples, (a) -> a.getColor().equals("red"));
        System.out.println(apples1);
    }

    private static void use01() {
        List<Apple> apples = Arrays.asList(new Apple("red", 150), new Apple("green", 120), new Apple("green", 120));

        List<Apple> result = filterApple(apples, new AppleFiler() {
            @Override
            public boolean filter(Apple apple) {
                return apple.getColor().equals("green");
            }
        });

        System.out.println(result);
    }

    /**
     * 传统过滤
     *
     * @param source
     * @param appFiler
     * @return
     */
    private static List<Apple> filterApple(List<Apple> source, AppleFiler appFiler) {
        List<Apple> appList = new ArrayList<>();
        for (Apple apple : source) {
            if (appFiler.filter(apple)) {
                appList.add(apple);
            }
        }
        return appList;
    }

    /**
     * lambda过滤
     *
     * @param source
     * @param predicate
     * @return
     */
    private static List<Apple> filterAppleByLambda(List<Apple> source, Predicate<Apple> predicate) {
        List<Apple> appList = new ArrayList<>();
        for (Apple apple : source) {
            if (predicate.test(apple)) {
                appList.add(apple);
            }
        }
        return appList;
    }


}
