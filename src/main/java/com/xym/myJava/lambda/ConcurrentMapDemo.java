package com.xym.myJava.lambda;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-18 16:04
 */
public class ConcurrentMapDemo {
    public static void main(String[] args) {

        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        map.put("4", "d");

        //第一个参数parallelismThreshold阈值指定为1，强制使用多线程并行执行
        map.forEach(1, (k, v) -> {
            synchronized (ConcurrentMapDemo.class) {
                System.out.printf("key %s,value %s by thread %s", k, v, Thread.currentThread().getName());
                System.out.println();
            }
        });


        Object search = map.search(1, (k, v) -> {
            System.out.println("search--" + Thread.currentThread().getName() + "--" + k);
            if (k.equals("7")) {
                return v;
            }
            return null;
        });

        System.out.println(search);


        Object c = map.searchValues(1, v -> {
            if (v.equals("d")) {
                System.out.println("searchValues--" + Thread.currentThread().getName());
                return v;
            }
            return null;
        });

        System.out.println(c);

        Optional.ofNullable(map.reduce(1, (k, v) -> {
            return k + "=" + v;
        }, (s1, s2) -> {
            return s1 + "," + s2;
        })).ifPresent(System.out::println);
    }
}
