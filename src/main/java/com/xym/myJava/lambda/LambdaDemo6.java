package com.xym.myJava.lambda;

import java.time.Clock;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-13 17:26
 */
public class LambdaDemo6 {
    public static void main(String[] args) {
        Map<Integer, String> c = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            /*如果map中没有则插入*/
            c.putIfAbsent(i, "val " + i);
        }
        c.forEach((i, val) -> {
            System.out.println(i + "=" + val);
        });

        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        System.out.println(millis == System.currentTimeMillis());
        /*原始date*/
        Date from = Date.from(clock.instant());
        System.out.println(from);

        //可用的时区
        System.out.println(ZoneId.getAvailableZoneIds());
        ZoneId of = ZoneId.of("Asia/Shanghai");
        System.out.println(of.getRules());
        System.out.println(of.getId());

        LocalTime now = LocalTime.now(of);
        System.out.println(now);
    }
}
