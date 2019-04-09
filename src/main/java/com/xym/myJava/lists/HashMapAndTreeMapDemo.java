package com.xym.myJava.lists;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * TreeMap通过比较器是否为0去重，而HashMap则是通过hashcode和equals方法的返回值比较去重
 * 这点很重要
 *
 * @author xym
 * @create 2019-04-06 14:45
 */
public class HashMapAndTreeMapDemo {
    public static void main(String[] args) {
        TreeMap<Key, String> treeMap = new TreeMap<>();
        treeMap.put(new Key(), "one");
        treeMap.put(new Key(), "two");
        //treeMap的size为2
        System.out.println(treeMap.size());
        System.out.println(treeMap);
        HashMap<Key, String> hashMap = new HashMap<>();
        hashMap.put(new Key(), "one");
        hashMap.put(new Key(), "two");
        //hashMap的size为1
        System.out.println(hashMap.size());
        System.out.println(hashMap);
    }

    static class Key implements Comparable {
        @Override
        public int compareTo(Object o) {
            return -1;
        }

        /**
         * 保持相同的hashcode和equals
         */
        @Override
        public int hashCode() {
            return 1;
        }

        /**
         * 保持相同的hashcode和equals
         */
        @Override
        public boolean equals(Object obj) {
            return true;
        }
    }
}
