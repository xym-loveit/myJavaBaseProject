package com.xym.myJava.search;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author xym
 */
public class Test1 {
    public static void main(String[] args) {
        int[] ints = {14, 5, 18, 2, 8, 1, 16};
        Arrays.sort(ints);
        System.out.println(4 >>>16);
        int i = Arrays.binarySearch(ints, 4);

        System.out.println(Arrays.toString(ints));
        /**
         * 输出：(当前插入点+1)=-3，表示当前插入点为位置2
         *
         * 也就是说如果当前插入查找的改值在该位置，则可以保持当前顺序无误
         */
        System.out.println("(当前插入点+1)=" + i);

        /**
         * 赛选同时指定比较器
         */
        System.out.println(Arrays.binarySearch(new Integer[]{8, 5, 2, 15, 18, 6}, 7, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        }));

        Integer[] aaa = new Integer[]{8, 5, 2, 15, 18, 6};
        Arrays.fill(aaa, 100);
        System.out.println(Arrays.toString(aaa));
    }
}
