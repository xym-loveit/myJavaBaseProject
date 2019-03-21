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
 * 当前实例下，直播房间topic1 会丢失一部分用户-------------------有缺陷
 *
 * @author xym
 * @create 2019-03-21 15:24
 */
public class ConcurrentHashMapTest {

    //创建map，key为topic，value为设备列表
    static ConcurrentHashMap<String, List<String>> hashMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        //进入直播间topic1，线程one
        Thread thread1 = new Thread(() -> {
            ArrayList<String> list = new ArrayList<>();
            list.add("device1");
            list.add("device2");
            hashMap.put("topic1", list);
            System.out.println(JSON.toJSONString(hashMap));
        });

        Thread thread2 = new Thread(() -> {
            ArrayList<String> list = new ArrayList<>();
            list.add("device11");
            list.add("device22");
            hashMap.put("topic1", list);
            System.out.println(JSON.toJSONString(hashMap));
        });

        Thread thread3 = new Thread(() -> {
            ArrayList<String> list = new ArrayList<>();
            list.add("device111");
            list.add("device222");
            hashMap.put("topic2", list);
            System.out.println(JSON.toJSONString(hashMap));
        });


        thread1.start();
        thread2.start();
        thread3.start();
    }
}
