package com.xym.myJava.jdk8.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * java8实践--5.5练习
 *
 * @author xym
 * @create 2019-07-08 0:06
 */
public class PracticeMain {

    static List<Transaction> transactions = null;

    static {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    public static void main(String[] args) {
        //p01();
        //p02();
        //p03();
        //p04();
        //p05();
        //p06();
        //p07();
        p08();

    }

    private static void p08() {
        //找到交易额最小的交易。
        //Optional<Trader> trader = transactions.stream().sorted(Comparator.comparing(Transaction::getValue)).findFirst().map(Transaction::getTrader);
        //System.out.println(trader.get());

        Optional<Transaction> reduce = transactions.stream().reduce((transaction, transaction2) -> transaction.getValue() < transaction2.getValue() ? transaction : transaction2);
        System.out.println(reduce);
    }

    /**
     * 所有交易中，最高的交易额是多少？
     */
    private static void p07() {
        System.out.println(transactions.stream().map(Transaction::getValue).reduce(Integer::max));
    }

    /**
     * 打印生活在剑桥的交易员的所有交易额
     */
    private static void p06() {
        //打印生活在剑桥的交易员的所有交易额
        System.out.println(transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue).reduce(0, Integer::sum));
    }

    /**
     * 有没有交易员是在米兰工作的？
     */
    private static void p05() {
        System.out.println(transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan")));
    }

    /**
     * 返回所有交易员的姓名字符串，按字母顺序排序
     */
    private static void p04() {
        System.out.println(transactions.stream().map(transaction -> transaction.getTrader().getName()).distinct().sorted().collect(Collectors.joining()));
    }

    /**
     * 查找所有来自于剑桥的交易员，并按姓名排序。
     */
    private static void p03() {
        System.out.println(transactions.stream().map(transaction -> transaction.getTrader()).filter(trader -> trader.getCity().equals("Cambridge")).distinct().sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList()));
    }

    private static void p02() {
        //交易员都在哪些不同的城市工作过？
        System.out.println(transactions.stream().map(transaction -> transaction.getTrader().getCity()).distinct().collect(Collectors.toList()));
        //set结构自动去重
        System.out.println(transactions.stream().map(transaction -> transaction.getTrader().getCity()).collect(Collectors.toSet()));
    }

    /**
     * 找出2011年发生的所有交易，并按交易额排序
     */
    private static void p01() {
        Stream<Transaction> transactionStream = transactions.stream().filter(transaction -> transaction.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue));
        transactionStream.forEach(System.out::println);
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
        private final Trader trader;
        private final int year;
        private final int value;

        public Transaction(Trader trader, int year, int value) {
            this.trader = trader;
            this.year = year;
            this.value = value;
        }

        public Trader getTrader() {
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
