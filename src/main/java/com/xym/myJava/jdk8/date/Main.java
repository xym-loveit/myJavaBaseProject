package com.xym.myJava.jdk8.date;


import java.nio.file.attribute.FileTime;
import java.sql.Timestamp;
import java.text.Format;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

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
        //testTemporalAdjuster();
        //testDateTimeFormat();
        //testZoneId();
        //testZoneId2();
        //testZoneOffset();
        //testZoneDate();
        //legacyDateConvert();
        //availableZoneIds();
        //System.out.println(ZoneId.getAvailableZoneIds());
        //ZonedDateTime zonedDateTime = ZonedDateTime.now();
        //System.out.println(ZoneId.systemDefault());
        //System.out.println(zonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Tokyo")));
        //long until = Instant.ofEpochSecond(0).until(Instant.now(), ChronoUnit.DAYS);
        //System.out.println(until);
        //LocalDate start = LocalDate.of();
        //System.out.println(start.until(LocalDate.now(), ChronoUnit.DAYS));
        //getDaysInYear();
        //mondayAllOfOneMonth(10, DayOfWeek.FRIDAY);
        //testDay();
        //getListAllDayInYear();

    }

    /**
     * 找一年中是13号又是星期5的日期
     */
    private static void getListAllDayInYear() {
        //找一年中是13号又是星期5的日期
        int y = 2019;
        LocalDate localDate = Year.of(y).atMonth(1).atDay(1).with(TemporalAdjusters.firstInMonth(DayOfWeek.FRIDAY));
        int targetY = localDate.getYear();
        while (targetY == y) {
            //当前时间是不是13号
            Boolean query = localDate.query(d -> d.get(ChronoField.DAY_OF_MONTH) == 13);
            if (query) {
                System.out.println(localDate);
            }
            //下一个星期五
            localDate = localDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
            targetY = localDate.getYear();
        }
    }

    /**
     * 假设给定5月17号，判断该日期是否是17号并且还是星期五
     */
    private static void testDay() {
        // 假设给定5月17号，判断该日期是否是17号并且还是星期五
        int m = 5;
        int d = 17;
        LocalDate localDate = Year.now().atMonth(m).atDay(d);
        Boolean query = localDate.query(date -> {
            int dd = date.get(ChronoField.DAY_OF_MONTH);
            int ww = date.get(ChronoField.DAY_OF_WEEK);
            return ww == 5 && dd == 17;
        });
        System.out.println(query);
    }

    /**
     * 在当年的某个特定月份，列出当月的所有指定星期
     *
     * @param month（月份）
     * @param dayOfWeek（星期几）
     */
    private static void mondayAllOfOneMonth(int month, DayOfWeek dayOfWeek) {
        //在当年的某个特定月份，列出当月的所有星期一
        //int m = 5;
        Month of = Month.of(month);
        LocalDate localDate = Year.now().atMonth(month).atDay(1).
                //找到该月的第一个星期1并作为起始日期
                        with(TemporalAdjusters.firstInMonth(dayOfWeek));
        Month mi = localDate.getMonth();
        System.out.printf("%s的" + dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()) + "有以下几天:%n", mi.getDisplayName(TextStyle.FULL, Locale.getDefault()));
        while (mi == of) {
            System.out.printf("%s%n", localDate);
            localDate = localDate.with(TemporalAdjusters.next(dayOfWeek));
            mi = localDate.getMonth();
        }
    }

    private static void getDaysInYear() {
        int year = 2019;
        //给定一个年份，打印出该年中每个月有多少天
        Arrays.stream(Month.values()).map(m -> LocalDate.of(year, m.getValue(), 1)).forEach(d -> {
            Month month = d.getMonth();
            String displayNameMonth = month.getDisplayName(TextStyle.FULL, Locale.getDefault());
            System.out.printf("%35s %10s%n", displayNameMonth, d.lengthOfMonth());
        });
    }

    private static void availableZoneIds() {
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        List<String> list = new ArrayList<>(availableZoneIds);
        Collections.sort(list);
        LocalDateTime dt = LocalDateTime.now();
        for (String s : list) {
            // 获取到的字符串可以通过ZoneId.of获取实例
            ZoneId zone = ZoneId.of(s);
            // 把本地时间加时区信息 转换成一个ZonedDateTime
            ZonedDateTime zdt = dt.atZone(zone);
            // 如果说一个时区是 +3;而北京是+8，那么该时区比北京慢5个小时
            // 北京时间是12点，那么该时区12-5 = 7
            ZoneOffset offset = zdt.getOffset();
            int secondsOfHour = offset.getTotalSeconds() % (60 * 60);
            String out = String.format("%35s %10s%n", zone, offset);
            if (secondsOfHour != 0) {
                System.out.printf(out);
            }
        }
    }

    /**
     * java.time类和遗留类之间的转换
     */
    private static void legacyDateConvert() {
        //date和Instant互转，2者等价
        Date from = Date.from(Instant.now());
        System.out.println(from);
        Instant instant = from.toInstant();
        System.out.println(instant);
        //GregorianCalendar和ZonedDateTime等价(带时区的日期时间)
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        GregorianCalendar from1 = GregorianCalendar.from(zonedDateTime);
        System.out.println(from1.getTime());
        ZonedDateTime zonedDateTime1 = from1.toZonedDateTime();
        System.out.println(zonedDateTime1);
        //Timestamp--Instant
        Timestamp timestamp = new Timestamp(from.getTime());
        System.out.println(timestamp);
        Instant instant1 = timestamp.toInstant();
        System.out.println(instant1);
        Timestamp from2 = Timestamp.from(Instant.now());
        System.out.println(from2);
        //Timestamp-->LocalDateTime
        LocalDateTime dateTime = timestamp.toLocalDateTime();
        System.out.println(dateTime);
        Timestamp valueOf = Timestamp.valueOf(dateTime);
        System.out.println(valueOf);
        //LocalDate--java.sql.Date
        java.sql.Date date = java.sql.Date.valueOf(LocalDate.now());
        System.out.println(date);
        LocalDate localDate = date.toLocalDate();
        System.out.println(localDate);
        //LocalTime--java.sql.Time
        java.sql.Time time = java.sql.Time.valueOf(LocalTime.now());
        LocalTime localTime = time.toLocalTime();
        //DateTimeFormatter-->java.text.DateFormatter
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = dateTimeFormatter.format(LocalDateTime.now());
        System.out.println(format);
        Format format1 = dateTimeFormatter.toFormat();
        //ZoneId---java.util.TimeZone
        TimeZone timeZone = TimeZone.getTimeZone(ZoneId.systemDefault());
        System.out.println(timeZone);
        ZoneId zoneId = timeZone.toZoneId();
        System.out.println(zoneId);
        //Instant--java.nio.file.attribute.FileTime
        FileTime from3 = FileTime.from(instant);
        System.out.println(from3);
        Instant instant2 = from3.toInstant();
        System.out.println(instant2);
    }

    private static void testZoneDate() {
        ZonedDateTime now = ZonedDateTime.now();
        String format = now.format(DateTimeFormatter.ofPattern("O yyyy-MM-dd HH:mm:ss"));
        System.out.println(format);
    }

    //英国伦敦格林尼治时间
    private static void testZoneOffset() {
        ZoneOffset of = ZoneOffset.of("+00:00");
        Instant now = Instant.now();
        System.out.println(now.atZone(of).format(DateTimeFormatter.ofPattern("O yyyy-MM-dd HH:mm:ss")));
    }

    private static void testZoneId2() {
        //System.out.println(ZoneId.getAvailableZoneIds());
        //第二种方式实现按地域分组，且无需定义额外对象
        Map<String, List<String>> collect = ZoneId.getAvailableZoneIds().stream().
                filter(s -> s.contains("/")).
                collect(Collectors.toMap(s -> s.split("/")[0], s -> {
                    List<String> values = new ArrayList<>();
                    values.add(s);
                    return values;
                }, (v1, v2) -> {
                    //如果冲突就将第二个产生的list添加进第一个
                    v1.addAll(v2);
                    return v1;
                }));

        System.out.println(collect);
    }

    private static void testZoneId() {
        System.out.println(ZoneId.systemDefault());
        System.out.println(ZoneId.getAvailableZoneIds().stream().collect(Collectors.toList()));
        //按地域分组
        Map<String, List<ZoneClazz>> collect = ZoneId.getAvailableZoneIds().stream().filter(s -> s.contains("/")).map(zs -> {
                    try {
                        return new ZoneClazz(zs.split("/")[0], zs);
                    } catch (Exception e) {
                        System.out.println(zs);
                        e.printStackTrace();
                        throw e;
                    }
                }
        ).
                collect(Collectors.groupingBy(ZoneClazz::getArea));
        System.out.println(collect);
    }

    static class ZoneClazz {
        private String area;
        private String zone;

        public ZoneClazz(String area, String zone) {
            this.area = area;
            this.zone = zone;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getZone() {
            return zone;
        }

        public void setZone(String zone) {
            this.zone = zone;
        }

        @Override
        public String toString() {
            return zone;
        }

    }

    private static void testDateTimeFormat() {
        LocalDate now = LocalDate.now();
        System.out.println(now.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println(now.format(DateTimeFormatter.ISO_DATE));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        System.out.println(dateTimeFormatter.format(now));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM-dd", Locale.ITALIAN);
        System.out.println(now.format(formatter));
    }

    //日期时间调节器，比较重要,比较实用
    private static void testTemporalAdjuster() {
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
        //通过lambda表达式实现日期调节器
        TemporalAdjuster nextWorkingDay4Lambda = TemporalAdjusters.ofDateAdjuster(localDate -> {
            DayOfWeek of = DayOfWeek.of(localDate.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (of == DayOfWeek.FRIDAY) {
                dayToAdd = 3;
            } else if (of == DayOfWeek.SATURDAY) {
                dayToAdd = 2;
            }
            return localDate.plus(dayToAdd, ChronoUnit.DAYS);
        });
        System.out.println(now.with(nextWorkingDay4Lambda));
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
