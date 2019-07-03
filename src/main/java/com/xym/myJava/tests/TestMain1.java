package com.xym.myJava.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-06-28 10:11
 */
public class TestMain1 {
    public static void main(String[] args) {
        System.out.println(TimeUnit.MINUTES.toMillis(5));
        TestMain1 main1 = new TestMain1();
        List<Long> list = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            System.out.print(main1.computeSleepTime(i, 1000 * 10 * i, 60 * 60 * 500 * 1));
            long time = main1.computeSleepTimeWithFib(i, 2, 15);
            list.add(time);
            System.out.print("----" + time);
            System.out.println();
        }
        list.stream().reduce((aLong, aLong2) -> aLong + aLong2).ifPresent(System.out::println);
    }

    public long computeSleepTime(int attemptNumber, int multiplier, int maximumWait) {
        double exp = Math.pow(2, attemptNumber);
        long result = Math.round(multiplier * exp);
        if (result > maximumWait) {
            result = maximumWait;
        }
        return result >= 0L ? result : 0L;
    }

    public long computeSleepTimeWithFib(int attemptNumber, int multiplier, int maximumWait) {
        long fib = fib(attemptNumber);
        long result = multiplier * fib;

        if (result > maximumWait || result < 0L) {
            result = maximumWait;
        }

        return result >= 0L ? result : 0L;
    }

    private long fib(long n) {
        if (n == 0L) {
            return 0L;
        }
        if (n == 1L) {
            return 1L;
        }

        long prevPrev = 0L;
        long prev = 1L;
        long result = 0L;

        for (long i = 2L; i <= n; i++) {
            result = prev + prevPrev;
            prevPrev = prev;
            prev = result;
        }

        return result;
    }
}
