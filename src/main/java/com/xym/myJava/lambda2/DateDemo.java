package com.xym.myJava.lambda2;

import java.time.Clock;
import java.time.ZoneId;
import java.util.Date;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-06-10 16:58
 */
public class DateDemo {
    public static void main(String[] args) {
        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.millis());
        System.out.println(clock.getZone());
        System.out.println(clock.instant());
        System.out.println(Date.from(clock.instant()));
        System.out.println(ZoneId.getAvailableZoneIds());
        System.out.println(ZoneId.of("Asia/Shanghai").toString());

    }
}
