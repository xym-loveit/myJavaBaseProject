package com.xym.myJava;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author xym
 */
public class ClockDemo {
    public static void main(String[] args) {
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        System.out.println(millis);

        Instant instant = clock.instant();
        Date from = Date.from(instant);
        System.out.println(from);

        System.out.println(ZoneId.getAvailableZoneIds());
        ZoneId of = ZoneId.of("Asia/Aden");
        ZoneId of2 = ZoneId.of("Asia/Shanghai");
        System.out.println(of.getRules());
        System.out.println(of2.getRules());
    }
}
