package com.xym.myJava.jdk8.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-07-19 13:49
 */
public class Main {
    public static void main(String[] args) {
        //testLocalDate();
        //testInstant();
        //testDuration();
        //testPeriod();
        LocalDate now = LocalDate.now();
        //根据当前时间最近的一个星期四
        LocalDate with = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.THURSDAY));
        System.out.println(with);
        //根据当前时间最近的星期五，当前为星期五直接返回
        LocalDate with1 = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY));
        System.out.println(with1);
        //根据当前时间最近的星期四，当前为星期五，也就是直接返回前一天
        LocalDate with2 = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY));
        System.out.println(with2);
        //创建一个新的日期，它的值为同一个月中哪一周的星期几
        //（2，DayOfWeek.THURSDAY），表示当前月的第二个星期的星期四
        LocalDate with3 = now.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.THURSDAY));
        System.out.println(with3);
        //当前月份的第一天
        System.out.println(now.with(TemporalAdjusters.firstDayOfMonth()));
        //下个月份的第一天
        System.out.println(now.with(TemporalAdjusters.firstDayOfNextMonth()));
        //明年的第一天
        System.out.println(now.with(TemporalAdjusters.firstDayOfNextYear()));
        //当年的第一天
        System.out.println(now.with(TemporalAdjusters.firstDayOfYear()));
        //当月第一个星期五
        System.out.println(now.with(TemporalAdjusters.firstInMonth(DayOfWeek.FRIDAY)));
        //当月的最后一天
        System.out.println(now.with(TemporalAdjusters.lastDayOfMonth()));
        System.out.println(now.with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY)));
        //自定义实现下一个工作日
        NextWorkingDay nextWorkingDay = new NextWorkingDay();
        //System.out.println(nextWorkingDay.adjustInto(LocalDate.now().plusDays(-2)));
        System.out.println(now.with(nextWorkingDay));
    }

    private static void testPeriod() {
        Period of = Period.of(2019, 07, 19);
        System.out.println(of.toTotalMonths());
        System.out.println(of.withYears(2018));
        //
        Period between = Period.between(LocalDate.of(2019, 7, 18), LocalDate.of(2019, 7, 20));
        System.out.println(between.getDays());
        Duration duration = Duration.ofMinutes(20);
        //创建Duration的方法
        Duration duration2 = Duration.of(20, ChronoUnit.MINUTES);
        System.out.println(duration.toString());
        System.out.println(duration2.toString());
        Period period = Period.ofDays(2);
        Period period1 = Period.ofWeeks(2);
        Period of1 = Period.of(2019, 7, 19);
        System.out.println(period1);
    }

    private static void testDuration() {
        LocalTime localTime = LocalTime.of(14, 54, 23);
        LocalTime localTime2 = LocalTime.of(15, 51, 02);
        //2个LocalTime时间间隔
        Duration between = Duration.between(localTime, localTime2);
        //间隔毫秒数
        System.out.println(between.toMillis());
        //间隔秒数
        System.out.println(between.getSeconds());
        //2个LocalDateTime时间间隔
        LocalDateTime startInclusive = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        LocalDateTime endExclusive = LocalDateTime.now().plusDays(2);
        Duration between1 = Duration.between(startInclusive, endExclusive);
        //间隔小时
        System.out.println(between1.toHours());
        //2个Instant时间间隔
        Duration between2 = Duration.between(Instant.now(), Instant.now().plus(Duration.ofHours(800)));
        System.out.println(between2.toHours());
    }

    private static void testInstant() {
        Instant instant = Instant.ofEpochSecond(3);
        Instant instant1 = Instant.ofEpochSecond(3, 0);
        Instant instant2 = Instant.ofEpochSecond(2, 1_000_000_000);
        Instant instant3 = Instant.ofEpochSecond(4, -1_000_000_000);
        System.out.println(instant);
        System.out.println(instant2);
        System.out.println(instant3);
        System.out.println(instant1);
    }

    private static void testLocalDate() {
        //将字符串格式化为日期或时间
        LocalDate date = LocalDate.parse("2019-07-19", DateTimeFormatter.ISO_DATE);
        LocalTime time = LocalTime.parse("14:25:59", DateTimeFormatter.ISO_TIME);
        //System.out.println(date);
        //System.out.println(time);
        //通过年月日时分秒实例化LocalDateTime
        LocalDateTime dateTime = LocalDateTime.of(2019, Month.JULY, 19, 14, 27, 39);
        //通过date和time组合为LocalDateTime
        LocalDateTime dateTime1 = LocalDateTime.of(date, time);
        System.out.println(dateTime);
        System.out.println(dateTime1);
        //date和小时、分钟、秒组合为LocalDateTime
        LocalDateTime dateTime2 = date.atTime(14, 28, 29);
        System.out.println(dateTime2);
        //date和time组合为LocalDateTime
        LocalDateTime dateTime3 = date.atTime(time);
        System.out.println(dateTime3);
        //time和date组合为LocalDateTime
        LocalDateTime dateTime4 = time.atDate(date);
        System.out.println("dateTime4-" + dateTime4);
        //LocalDateTime转为LocalDate
        LocalDate date1 = dateTime4.toLocalDate();
        System.out.println(date1);
        //LocalDateTime转为LocalTime
        LocalTime time1 = dateTime4.toLocalTime();
        System.out.println(time1);
    }
}
