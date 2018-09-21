package com.xym.myJava.guava;

import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultiset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-19 15:23
 */
public class CollectionDemo {
    public static void main(String[] args) {

        /**
         * list有序可以重复
         * set无序不可重复
         * Multiset无序可以重复
         */
        HashMultiset<String> strings = HashMultiset.create();
        strings.add("a");
        strings.add("a");
        strings.add("b");
        strings.add("c");
        strings.add("d");
        //Multiset自带一个有用的功能，就是可以跟踪每个对象的数量。
        System.out.println(strings.count("a"));


        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add("b");
        //这种视图不够安全，不是真正意义上的快照，怎么能随着变化呢？
        List<String> unmodifiableList = Collections.unmodifiableList(arrayList);
        //操作原对象结果导致安全对象也被改变了
        arrayList.add("c");
        System.out.println(unmodifiableList.size());

        //采用保护性拷贝解决Defensive Copies
        List<String> unmodifiableList2 = Collections.unmodifiableList(new ArrayList<>(arrayList));


        HashBiMap<Object, Object> hashBiMap = HashBiMap.create();
        hashBiMap.put("xym","loveit1");
        //hashBiMap.put("123","loveit");
        hashBiMap.forcePut("xym","loveit2");

        System.out.println(hashBiMap.inverse().get("loveit2"));
    }
}
