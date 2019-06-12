package com.xym.myJava.lambda2;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-06-10 16:14
 */
public class MapDemo {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }
        map.forEach((id, val) -> System.out.println(val));
        //如果key为3则值修改为后面的lambda表达式结果
        map.computeIfPresent(3, (num, val) -> val + num);
        System.out.println(map);
        //如果key为9存在，则将值置空
        map.computeIfPresent(9, (k, v) -> null);
        System.out.println(map);
        //如果key为23不存在（Absent）则添加值为val23
        map.computeIfAbsent(23, (k) -> "val" + k);
        System.out.println(map);
        map.computeIfAbsent(3, num -> "bam");
        System.out.println(map.get(3));
        System.out.println(map.getOrDefault(33, "val33"));
        //合并操作先看map中是否没有特定的key/value存在，如果是，则把key/value存入
        //map，否则merging函数就会被调用，对现有的数值进行修改
        map.merge(9, "val9", (value, newValue) -> value.concat(newValue)
        );
        map.merge(9, "----", (value, newValue) -> value.concat(newValue)
        );
        System.out.println(map);
        map.merge(9, "ooooo", (value, newValue) -> null
        );
        System.out.println(map);
    }
}
