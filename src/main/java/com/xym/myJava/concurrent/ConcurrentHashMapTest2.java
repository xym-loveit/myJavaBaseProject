package com.xym.myJava.concurrent;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 模拟一个直播的场景，每个直播对应一个topic，每个用户进入直播时，会把自己的设备ID绑定到这个topic上，也就是一个topic
 * 对应一堆设备，用户可以使用map来维护这些信息，其中key为topic，value为设备的list
 * <p>
 * <p>
 * 采用新方法（putIfAbsent）代替老方法（put）解决问题---ConcurrentHashMapTest改良版
 * <p>
 * <p>
 * put方法判断如果key已经存在，则使用value覆盖原来的值，并返回原来的值，如果不存在把value放入并返回null，
 * 而putIfAbsent方法则是如果key已经存在则直接返回原来对应的值并不使用value覆盖，如果key不存在，则放入value并返回null
 * ，且判断key是否存在和放入是原子性操作
 *
 * @author xym
 * @create 2019-03-21 15:24
 */
public class ConcurrentHashMapTest2 {

    //创建map，key为topic，value为设备列表
    static ConcurrentHashMap<String, List<String>> hashMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        //进入直播间topic1，线程one
        Thread thread1 = new Thread(() -> {
            ArrayList<String> list = new ArrayList<>();
            list.add("device1");
            list.add("device2");
            List<String> topic1 = hashMap.putIfAbsent("topic1", list);
            if (topic1 != null) {
                topic1.addAll(list);
            }
            System.out.println(JSON.toJSONString(hashMap));
        });

        Thread thread2 = new Thread(() -> {
            ArrayList<String> list = new ArrayList<>();
            list.add("device11");
            list.add("device22");
            List<String> topic1 = hashMap.putIfAbsent("topic1", list);
            if (topic1 != null) {
                topic1.addAll(list);
            }
            System.out.println(JSON.toJSONString(hashMap));
        });

        Thread thread3 = new Thread(() -> {
            ArrayList<String> list = new ArrayList<>();
            list.add("device111");
            list.add("device222");
            List<String> topic2 = hashMap.putIfAbsent("topic2", list);
            if (topic2 != null) {
                topic2.addAll(list);
            }
            System.out.println(JSON.toJSONString(hashMap));
        });


        thread1.start();
        thread2.start();
        thread3.start();
    }
}
