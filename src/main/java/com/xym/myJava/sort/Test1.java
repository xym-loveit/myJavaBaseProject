package com.xym.myJava.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author xym
 */
public class Test1 {
    public static void main(String[] args) {

        String[] arrayStr = {"Break", "zero", "three", "hystrix", "zuul", "eureka", "serverConfig", "feign", "docker", "apache"};
        String[] arrayStr2 = {"Break", "zero", "three", "hystrix", "zuul", "eureka", "serverConfig", "feign", "docker", "apache"};
        String[] arrayStr3 = {"Break", "zero", "three", "hystrix", "zuul", "eureka", "serverConfig", "feign", "docker", "apache"};
        String[] arrayStr4 = {"Break", "zero", "three", "hystrix", "zuul", "eureka", "serverConfig", "feign", "docker", "apache"};

        Arrays.sort(arrayStr);

        System.out.println("区分大小写的排序=" + Arrays.toString(arrayStr));

        /**
         * 使用string 自带的比较器
         */
        Arrays.sort(arrayStr2, String.CASE_INSENSITIVE_ORDER);

        System.out.println("不区分大小写的排序=" + Arrays.toString(arrayStr2));

        /**
         * 使用不区分大小写且倒置顺序的比较器（从大到小）
         */
        Arrays.sort(arrayStr3, Collections.<String>reverseOrder(String.CASE_INSENSITIVE_ORDER));

        System.out.println("不区分大小写的排序,按逆序=" + Arrays.toString(arrayStr3));

        Arrays.sort(arrayStr4, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                /*调换o2和o1的比较顺序*/
                return o2.compareTo(o1);
//                o2.compareToIgnoreCase(o1);
            }
        });

        System.out.println("区分大小写的排序,按逆序=" + Arrays.toString(arrayStr4));


    }
}
