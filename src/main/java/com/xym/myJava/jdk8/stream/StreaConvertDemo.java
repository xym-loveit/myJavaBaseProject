package com.xym.myJava.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 流的转换
 *
 * @author xym
 * @create 2019-07-08 9:57
 */
public class StreaConvertDemo {


    static List<PracticeMain.Transaction> transactions = null;

    static {
        PracticeMain.Trader raoul = new PracticeMain.Trader("Raoul", "Cambridge");
        PracticeMain.Trader mario = new PracticeMain.Trader("Mario", "Milan");
        PracticeMain.Trader alan = new PracticeMain.Trader("Alan", "Cambridge");
        PracticeMain.Trader brian = new PracticeMain.Trader("Brian", "Cambridge");
        transactions = Arrays.asList(
                new PracticeMain.Transaction(brian, 2011, 300),
                new PracticeMain.Transaction(raoul, 2012, 1000),
                new PracticeMain.Transaction(raoul, 2011, 400),
                new PracticeMain.Transaction(mario, 2012, 710),
                new PracticeMain.Transaction(mario, 2012, 700),
                new PracticeMain.Transaction(alan, 2012, 950)
        );
    }


    public static void main(String[] args) {
        //c01();
        //c02();
        //op1();
        //range();
    }

    private static void range() {
        //包括2端
        long count = IntStream.rangeClosed(1, 100).filter(value -> value % 2 == 0).count();
        System.out.println(count);
        //不包括右端
        System.out.println(IntStream.range(1, 100).filter(value -> value % 2 == 0).count());
    }

    private static void op1() {
        //OptionalInt
        OptionalInt max = transactions.stream().mapToInt(PracticeMain.Transaction::getValue).max();
        int m = max.orElse(1);
        System.out.println("max=" + m);
    }

    /**
     * 将数值流转换回普通流
     */
    private static void c02() {
        //stream-->intStream
        IntStream intStream = transactions.stream().mapToInt(PracticeMain.Transaction::getValue);
        //再将数值流转换回普通流--intStream-->stream
        Stream<Integer> boxed = intStream.boxed();
        boxed.forEach(System.out::println);
    }

    /**
     * 流转为数值流-->避免装箱的复杂性
     */
    private static void c01() {
        //stream-->intStream
        IntStream intStream = transactions.stream().mapToInt(PracticeMain.Transaction::getValue);
        System.out.println(intStream.sum());
        intStream = transactions.stream().mapToInt(PracticeMain.Transaction::getValue);
        System.out.println(intStream.max().getAsInt());
        intStream = transactions.stream().mapToInt(PracticeMain.Transaction::getValue);
        System.out.println(intStream.average().getAsDouble());
    }

    static public class Trader {
        private final String name;
        private final String city;

        public Trader(String n, String c) {
            this.name = n;
            this.city = c;
        }

        public String getName() {
            return this.name;
        }

        public String getCity() {
            return this.city;
        }

        @Override
        public String toString() {
            return "Trader:" + this.name + " in " + this.city;
        }
    }

    static public class Transaction {
        private final PracticeMain.Trader trader;
        private final int year;
        private final int value;

        public Transaction(PracticeMain.Trader trader, int year, int value) {
            this.trader = trader;
            this.year = year;
            this.value = value;
        }

        public PracticeMain.Trader getTrader() {
            return this.trader;
        }

        public int getYear() {
            return this.year;
        }

        public int getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return "{" + this.trader + ", " +
                    "year: " + this.year + ", " +
                    "value:" + this.value + "}";
        }
    }
}
