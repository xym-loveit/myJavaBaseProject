package com.xym.myJava.jdk8.date;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

/**
 * 自定义下一个工作日，日期调节器
 *
 * @author xym
 * @create 2019-07-19 16:16
 */
public class NextWorkingDay implements TemporalAdjuster {
    @Override
    public Temporal adjustInto(Temporal temporal) {
        //获得当前时间为星期几---转为DayOfWeek对象
        DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        int dayToAdd = 1;
        if (dayOfWeek == DayOfWeek.FRIDAY) {
            //如果是周五+3天
            dayToAdd = 3;
        } else if (dayOfWeek == DayOfWeek.SATURDAY) {
            //如果是周六+2天
            dayToAdd = 2;
        }
        //其他情况+1天
        return temporal.plus(dayToAdd, ChronoUnit.DAYS);
    }
}
