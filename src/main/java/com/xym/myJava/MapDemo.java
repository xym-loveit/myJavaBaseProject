package com.xym.myJava;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {
    public static void main(String[] args) {
        Map<Integer, String> stringMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            stringMap.putIfAbsent(i, "val" + i);
        }

        stringMap.forEach((k, v) -> System.out.println(k + "=" + v));

        String s = stringMap.computeIfPresent(3, (num, val) -> val + num);
        System.out.println(stringMap.get(3));
    }
}
