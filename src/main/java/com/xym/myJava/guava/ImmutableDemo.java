package com.xym.myJava.guava;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-19 15:33
 */
public class ImmutableDemo {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add("b");


        ImmutableList<String> of = ImmutableList.of("a", "b", "c", "d");
        //of.add("d");
        ImmutableList<String> strings = ImmutableList.copyOf(arrayList);
        System.out.println(strings);
        arrayList.add("c");
        System.out.println(strings);
        System.out.println(arrayList);

        ImmutableMap<String, Integer> of1 = ImmutableMap.of("xym", 21, "loveit", 30);
        System.out.println(of1);
        //of1.put("a",111);


        ArrayListMultimap<String, String> arrayListMultimap = ArrayListMultimap.create();
        arrayListMultimap.put("xym","man");
        arrayListMultimap.put("xym","22");
        arrayListMultimap.put("xym","it");
        arrayListMultimap.put("zhangsan","it");
        System.out.println(arrayListMultimap.get("xym"));
        System.out.println(arrayListMultimap.entries());
    }
}
